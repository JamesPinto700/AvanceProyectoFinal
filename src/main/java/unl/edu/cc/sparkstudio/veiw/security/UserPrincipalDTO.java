package unl.edu.cc.sparkstudio.veiw.security;

import jakarta.validation.constraints.NotNull;
import unl.edu.cc.sparkstudio.domain.security.ActionType;
import unl.edu.cc.sparkstudio.domain.security.User;

import java.io.Serializable;
import java.security.Principal;

/**
 * @author MacGyver2.0
 */
public class UserPrincipalDTO implements Principal, Serializable {
    private final User user;

    public UserPrincipalDTO(@NotNull User user) {
        this.user = user;
    }

    public boolean hasPermissionForPage(String pagePath) {
        return this.hasPermission(pagePath, ActionType.READ);
    }

    public boolean hasPermission(String resource, ActionType action) {
        return user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.matchWith(resource, action));
    }

    @Override
    public String getName() {
        return user.getName();
    }
}

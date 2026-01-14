package unl.edu.ec.jbrew.view.security;

/**
 * @author wduck (Wilman Chamba Z)
 */

import jakarta.validation.constraints.NotNull;
import unl.edu.ec.jbrew.domain.security.ActionType;
import unl.edu.ec.jbrew.domain.security.User;

import java.io.Serializable;
import java.security.Principal;

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


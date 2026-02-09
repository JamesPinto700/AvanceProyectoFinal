package unl.edu.cc.sparkstudio.veiw.security;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import unl.edu.cc.sparkstudio.bussines.SecurityFacade;
import unl.edu.cc.sparkstudio.domain.security.ActionType;
import unl.edu.cc.sparkstudio.domain.security.Role;
import unl.edu.cc.sparkstudio.domain.security.User;
import unl.edu.cc.sparkstudio.exception.EntityNotFoundException;

import java.io.Serial;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author MacGyver2.0
 */

@Named
@SessionScoped
public class UserSession implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserSession.class.getName());

    @Inject
    private SecurityFacade securityFacade;

    private User user;

    public void postLogin(@NotNull User user) throws EntityNotFoundException {
        logger.info("User logged in: " + user.getName());
        this.user = user;
        Set<Role> roles = securityFacade.findRolesWithPermissionByUser(this.user.getId());
        user.setRoles(roles);
    }

    public boolean hasPermissionForPage(String pagePath) {
        return this.hasPermission(pagePath, ActionType.READ);
    }

    public boolean hasPermission(String resource, ActionType action) {
        if (resource.equals("/index.xhtml")){
            return true;
        }
        return user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.matchWith(resource, action));
    }

    public boolean hasRole(@NotNull String roleName){
        if (user == null){
            return false;
        }
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    public User getUser() {
        return user;
    }
}

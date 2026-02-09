package unl.edu.cc.sparkstudio.veiw;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import unl.edu.cc.sparkstudio.bussines.SecurityFacade;
import unl.edu.cc.sparkstudio.domain.security.User;
import unl.edu.cc.sparkstudio.exception.CredentialInvalidException;
import unl.edu.cc.sparkstudio.exception.EntityNotFoundException;
import unl.edu.cc.sparkstudio.faces.FacesUtil;
import unl.edu.cc.sparkstudio.veiw.security.UserPrincipalDTO;
import unl.edu.cc.sparkstudio.veiw.security.UserSession;

import java.util.logging.Logger;

/**
 * @author MacGyver2.0
 */

@Named
@ViewScoped
public class AuthenticationController implements java.io.Serializable {

    private static Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @NotNull @NotEmpty @Size(min=5, message = "Nombre de usuario muy corto")
    private String username;

    @NotNull @NotEmpty @Size(min=8, message = "Contraseña muy corta")
    private String password;

    @Inject
    private SecurityFacade securityFacade;

    @Inject
    private UserSession userSession;

    public String login(){
        logger.info("Login attempt for user: " + username);
        logger.info("Password: " + password);
        try {
            User user = securityFacade.authenticate(username, password);
            setHttpSession(user);
            FacesUtil.addSuccessMessageAndKeep("Aviso", "Bienvenido " + user.getName());
            userSession.postLogin(user);
            return "dashboard.xhtml?faces-redirect=true";

        } catch (CredentialInvalidException | EntityNotFoundException e) {
            FacesUtil.addErrorMessage("Inconveniente", e.getMessage());
            return null;
        }
    }

    /**
     * Establece la session de usuario en el contexto HTTTP de la aplicación
     * @param user
     */
    private void setHttpSession(User user){
        FacesContext context = FacesContext.getCurrentInstance();
        UserPrincipalDTO userPrincipal = new UserPrincipalDTO(user);
        context.getExternalContext().getSessionMap().put("user", userPrincipal);
    }

    public String logout() throws ServletException {
        FacesUtil.addSuccessMessageAndKeep(userSession.getUser().getName(), "Hasta pronto");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        ((jakarta.servlet.http.HttpServletRequest) facesContext.getExternalContext().getRequest()).logout();
        return "/index.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

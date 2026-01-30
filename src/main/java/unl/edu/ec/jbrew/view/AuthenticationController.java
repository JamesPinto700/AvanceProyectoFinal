package unl.edu.ec.jbrew.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import unl.edu.ec.jbrew.business.SecurityFacade;
import unl.edu.ec.jbrew.domain.security.User;
import unl.edu.ec.jbrew.exception.CredentialInvalidException;
import unl.edu.ec.jbrew.exception.EntityNotFoundException;
import unl.edu.ec.jbrew.faces.FacesUtil;
import unl.edu.ec.jbrew.view.security.UserPrincipalDTO;
import unl.edu.ec.jbrew.view.security.UserSession;

import java.util.logging.Logger;

@Named
@ViewScoped
public class AuthenticationController implements java.io.Serializable{

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
            /*
            FacesMessage fcm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso",
                    "Bienvenido " + user.getName());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fcm);
            fc.getExternalContext().getFlash().setKeepMessages(true);
            */
            userSession.postLogin(user);
            return "dashboard.xhtml?faces-redirect=true";

        } catch (CredentialInvalidException | EntityNotFoundException e) {
            /*FacesMessage fcm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso",
                    e.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fcm
             */
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







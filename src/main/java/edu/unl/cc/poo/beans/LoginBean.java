package edu.unl.cc.poo.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.io.IOException;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn;
    private String currentUser;

    public LoginBean() {
        this.loggedIn = false;
    }

    public void login() {
        // Usuario demo fijo
        if ("demo".equals(username) && "demo".equals(password)) {
            loggedIn = true;
            currentUser = username;

            try {
                // Redirigir al index
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Mostrar mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Credenciales incorrectas",
                            "Usuario o contraseña incorrectos"));
        }
    }

    public void logout() {
        loggedIn = false;
        currentUser = null;
        username = null;
        password = null;

        try {
            // Redirigir al login
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FacesContext.getCurrentInstance()
                            .getExternalContext().getRequestContextPath() + "/login.xhtml");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToRegister() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FacesContext.getCurrentInstance()
                            .getExternalContext().getRequestContextPath() + "/register.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Verificar si está autenticado (para usar en las páginas)
    public void checkLogin() {
        if (!loggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }

    public String getCurrentUser() { return currentUser; }
    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }
}
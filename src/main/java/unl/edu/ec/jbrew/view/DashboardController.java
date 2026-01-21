package unl.edu.ec.jbrew.view;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unl.edu.ec.jbrew.business.JBrewFacade;
import unl.edu.ec.jbrew.domain.Product;
import unl.edu.ec.jbrew.view.security.UserSession;

import java.util.List;

@Named
@ViewScoped
public class DashboardController implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private UserSession userSession;

    @Inject
    private JBrewFacade jBrewFacade;


    public DashboardController(){
    }

    public List<Product> getAllProducts(){
        return jBrewFacade.getAllProducts();
    }


}

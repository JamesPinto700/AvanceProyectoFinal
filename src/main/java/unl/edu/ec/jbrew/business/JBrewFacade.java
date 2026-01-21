package unl.edu.ec.jbrew.business;

import jakarta.ejb.Stateless;
import unl.edu.ec.jbrew.domain.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JBrewFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Product> getAllProducts(){
        return new ArrayList<Product>();
    }

}

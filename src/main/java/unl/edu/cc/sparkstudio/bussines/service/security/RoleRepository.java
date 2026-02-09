package unl.edu.cc.sparkstudio.bussines.service.security;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.Query;
import unl.edu.cc.sparkstudio.bussines.service.CrudGenericService;
import unl.edu.cc.sparkstudio.domain.security.Role;
import unl.edu.cc.sparkstudio.exception.EntityNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MacGyver2.0
 */

@Stateless
public class RoleRepository {
    @Inject
    private CrudGenericService crudService;

    public Set<Role> findAllWithPermissions(){
        return new HashSet<>(crudService.findWithQuery("Select * from Role"));
    }

    public Role find(String name) throws EntityNotFoundException {
        String sqlNative = "SELECT * FROM ROLE WHERE lower(NAME) LIKE ?";
        Query query = crudService.createNativeQuery(sqlNative, Role.class);
        query.setParameter(1, name.toLowerCase());
        Role entity = (Role) crudService.findSingleResultOrNullWithQuery(query);
        if (entity != null){
            return entity;
        }
        throw new EntityNotFoundException("Role no encontrado [" + name + "]");
    }
}

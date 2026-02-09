package unl.edu.cc.sparkstudio.bussines.service.security;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import unl.edu.cc.sparkstudio.bussines.service.CrudGenericService;
import unl.edu.cc.sparkstudio.domain.security.Permission;
import unl.edu.cc.sparkstudio.exception.EntityNotFoundException;

import java.util.List;

/**
 * @author MacGyver2.0
 */

@Stateless
public class PermissionRepository {
    @Inject
    private CrudGenericService crudService;


    public List<Permission> findAll(){
        return crudService.findWithNativeQuery("Select * from permission",Permission.class);
    }

    public Permission find(Long id) throws EntityNotFoundException {
        Permission entity = crudService.find(Permission.class,id);
        if(entity != null){
            return entity;
        }
        throw new EntityNotFoundException("Permiso no encontrado [" + id + "]");
    }
}

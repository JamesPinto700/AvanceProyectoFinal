package edu.unl.cc.poo.beans.service.security;

import edu.unl.cc.poo.beans.service.CrudGenericService;
import edu.unl.cc.poo.exception.EntityNotFoundException;
import edu.unl.cc.poo.security.Permission;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

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

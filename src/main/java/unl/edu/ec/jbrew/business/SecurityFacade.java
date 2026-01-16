package unl.edu.ec.jbrew.business;

import unl.edu.ec.jbrew.business.service.RoleRepository;
import unl.edu.ec.jbrew.business.service.UserRepository;
import unl.edu.ec.jbrew.domain.security.Role;
import unl.edu.ec.jbrew.domain.security.User;
import unl.edu.ec.jbrew.exception.AlreadyEntityException;
import unl.edu.ec.jbrew.exception.CredentialInvalidException;
import unl.edu.ec.jbrew.exception.EncryptorException;
import unl.edu.ec.jbrew.exception.EntityNotFoundException;
import unl.edu.ec.jbrew.util.EncryptorManager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SecurityFacade implements Serializable {

    private UserRepository userRepository = new UserRepository();

    private RoleRepository roleRepository = new RoleRepository();

    public User createUser(User user) throws EncryptorException, AlreadyEntityException {
        String pwdEncripted = EncryptorManager.encrypt(user.getPassword());
        user.setPassword(pwdEncripted);
        try {
            userRepository.find(user.getName());
        } catch (EntityNotFoundException e) {
            User userPersisted = userRepository.save(user);
            return userPersisted;
        }
        throw new AlreadyEntityException("Usuario ya existe");
    }

    public User authenticate(String name, String password) throws CredentialInvalidException {
        try {
            User userFound = userRepository.find(name);
            String pwdEncrypted = EncryptorManager.encrypt(password);
            if (pwdEncrypted.equals(userFound.getPassword())){
                return userFound;
            }
            throw new CredentialInvalidException();
        } catch (EntityNotFoundException e) {
            throw new CredentialInvalidException();
        } catch (EncryptorException e) {
            throw new CredentialInvalidException("Credenciales incorrectas", e);
        }
    }

    public Set<Role> findAllRolesWithPermission(){
        return roleRepository.findAllWithPermissions();
    }

    public Set<Role> findRolesWithPermissionByUser(Long userId) throws EntityNotFoundException {
        User user = userRepository.find(userId);
        // Simulación de usuarios con rol ADMIN
        Role  role = roleRepository.find("ADMIN");
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(role);
        return roles;
    }


}

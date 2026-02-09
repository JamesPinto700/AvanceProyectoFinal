package unl.edu.cc.sparkstudio.bussines;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import unl.edu.cc.sparkstudio.bussines.service.security.RoleRepository;
import unl.edu.cc.sparkstudio.bussines.service.security.UserRepository;
import unl.edu.cc.sparkstudio.domain.security.Role;
import unl.edu.cc.sparkstudio.domain.security.User;
import unl.edu.cc.sparkstudio.exception.AlreadyEntityException;
import unl.edu.cc.sparkstudio.exception.CredentialInvalidException;
import unl.edu.cc.sparkstudio.exception.EncryptorException;
import unl.edu.cc.sparkstudio.exception.EntityNotFoundException;
import unl.edu.cc.sparkstudio.util.EncryptorManager;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MacGyver2.0
 */
@Stateless
public class SecurityFacade implements Serializable {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

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

    public User updateUser(User user) throws AlreadyEntityException, EncryptorException {
        if (user.getId() == null){
            return createUser(user);
        }
        String pwdEncrypted = EncryptorManager.encrypt(user.getPassword());
        user.setPassword(pwdEncrypted);
        try {
            User userFound = userRepository.find(user.getName());
            if  (!userFound.getId().equals(user.getId())){
                throw new AlreadyEntityException("Ya existe otro usuario con ese nombre");
            }
        } catch (EntityNotFoundException ignored) {
        }
        return userRepository.save(user);
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

    public List<User> findUsers(String criteria) throws EntityNotFoundException {
        return userRepository.findWithLike(criteria);
    }

    public User findUser(Long userId) throws EntityNotFoundException {
        return  userRepository.find(userId);
    }
}

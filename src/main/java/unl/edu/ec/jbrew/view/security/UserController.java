package unl.edu.ec.jbrew.view.security;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import business.service.UserService;
import business.service.RoleService;
import business.service.OrganizationService;
import model.security.User;
import model.security.Role;
import model.security.Organization;
import unl.edu.ec.jbrew.business.service.RoleRepository;
import unl.edu.ec.jbrew.business.service.UserRepository;
import unl.edu.ec.jbrew.domain.security.User;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserController implements Serializable {

    private List<User> users;
    private User selectedUser;
    private List<User> filteredUsers;
    private List<Role> roles;
    private List<Organization> organizations;

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleService;

    @Inject
    private OrganizationService organizationService;

    @PostConstruct
    public void init() {
        loadUsers();
        loadRoles();
        loadOrganizations();
        selectedUser = new User();
    }

    private void loadUsers() {
        users = userRepository.findAll();
    }

    private void loadRoles() {
        roles = roleService.findAll();
    }

    private void loadOrganizations() {
        organizations = organizationService.findAll();
    }

    public void save() {
        try {
            if (selectedUser.getId() == null) {
                userRepository.create(selectedUser);
                FacesUtil.addSuccessMessage("Usuario creado exitosamente");
            } else {
                userRepository.update(selectedUser);
                FacesUtil.addSuccessMessage("Usuario actualizado exitosamente");
            }
            loadUsers();
            selectedUser = new User();
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Error al guardar usuario: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            userRepository.delete(selectedUser);
            FacesUtil.addSuccessMessage("Usuario eliminado exitosamente");
            loadUsers();
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void prepareNew() {
        selectedUser = new User();
    }

    public void prepareEdit(User user) {
        selectedUser = user;
    }

    // Getters y Setters
    public List<User> getUsers() { return users; }
    public User getSelectedUser() { return selectedUser; }
    public void setSelectedUser(User selectedUser) { this.selectedUser = selectedUser; }
    public List<User> getFilteredUsers() { return filteredUsers; }
    public void setFilteredUsers(List<User> filteredUsers) { this.filteredUsers = filteredUsers; }
    public List<Role> getRoles() { return roles; }
    public List<Organization> getOrganizations() { return organizations; }
}

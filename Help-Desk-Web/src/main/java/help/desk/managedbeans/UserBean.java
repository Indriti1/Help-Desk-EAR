package help.desk.managedbeans;


import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import help.desk.entities.User;
import help.desk.services.UserService;
import help.desk.utils.UserRole;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    private User newUser = new User();
    private User selectedUser;
    private List<User> users;
    
    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String addUser() {
        userService.addUser(getNewUser());
        newUser = new User();
    	loadUsers();
    	return "manageUsers?faces-redirect=true";
    }

    public void editUser(User user) {
        userService.editUser(user);
        loadUsers();
    }
    
    public String updateUser() {
        userService.editUser(getSelectedUser());
    	loadUsers();
        return "manageUsers?faces-redirect=true";
    }
    
    public String prepareEdit(User user) {
        this.selectedUser = user;
        return "editUser?faces-redirect=true&id=" + user.getId();
    }

    public void deleteUser(User user) {
    	userService.deleteUser(user.getId()); 
    	loadUsers();
    }

    public List<User> getUsers() {
        if (users == null) {
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        users = userService.getAllUsers();
    }

    public UserRole[] getRoles() {
        return UserRole.values();
    }
}
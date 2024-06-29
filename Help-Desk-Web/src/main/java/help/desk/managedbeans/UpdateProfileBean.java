package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

import help.desk.entities.Project;
import help.desk.entities.User;
import help.desk.services.UserService;

@Named
@SessionScoped
public class UpdateProfileBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private String email;
    private String password;
    private String username;
    private User selectedUser;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void init() {
        email = sessionBean.getLoggedInUser().getEmail();
        password = sessionBean.getLoggedInUser().getPassword();
        username = sessionBean.getLoggedInUser().getUsername();
    }
    
    public String prepareEdit(User user) {
        this.selectedUser = user;
        return "updateProfile?faces-redirect=true&id=" + user.getId();
    }

    public String updateProfile() {
        userService.editUser(sessionBean.getLoggedInUser());
        return "employeeDashboard?faces-redirect=true";
    }
}
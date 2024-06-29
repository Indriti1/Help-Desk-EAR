package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import help.desk.entities.User;
import help.desk.services.UserService;
import help.desk.utils.UserRole;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private String username;
    private String password;
    private String userRole;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String login() {
        User user = userService.login(username, password);
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
            return null;
        }

        // Set the logged-in user in the session
        sessionBean.setLoggedInUser(user);
        switch (UserRole.valueOf(user.getRole().name())) {
            case ADMIN:
                return "adminDashboard?faces-redirect=true";
            case EMPLOYEE:
                return "employeeDashboard?faces-redirect=true";
            case CUSTOMER:
                return "customerDashboard?faces-redirect=true";
            default:
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid role selected", null));
                return null;
        }
    }
}
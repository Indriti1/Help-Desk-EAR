package help.desk.managedbeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import help.desk.entities.User;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String logout() {
        loggedInUser = null;
        return "index?faces-redirect=true";
    }
}
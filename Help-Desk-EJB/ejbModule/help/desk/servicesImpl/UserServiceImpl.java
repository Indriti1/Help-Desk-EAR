package help.desk.servicesImpl;

import java.util.List;
import help.desk.dao.UserDAO;
import help.desk.entities.User;
import help.desk.services.UserService;
import help.desk.utils.UserRole;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserServiceImpl implements UserService {
	
    @EJB
    private UserDAO userDAO;

    @Override
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
    
    @Override
    public void editUser(User user) {
    	userDAO.editUser(user);
    }
    
    @Override
	public void deleteUser(Long userId) {
		User user = userDAO.findByUserId(userId);
		if (user != null) {
			userDAO.deleteUser(user);
		}
	}

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return userDAO.findByUserId(userId);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        UserRole roleName;
        try {
        	roleName = UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle invalid role name
            return List.of();
        }
        return userDAO.findByUserRole(roleName);
    }
}
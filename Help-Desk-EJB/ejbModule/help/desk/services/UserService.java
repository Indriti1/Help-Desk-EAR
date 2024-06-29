package help.desk.services;

import java.util.List;
import help.desk.entities.User;

public interface UserService {
    User login(String username, String password);
    void addUser(User user);
    void editUser(User user);
	void deleteUser(Long userId);
    List<User> getAllUsers();
    User getUserById(Long userId);
	List<User> getUsersByRole(String role);
	
}

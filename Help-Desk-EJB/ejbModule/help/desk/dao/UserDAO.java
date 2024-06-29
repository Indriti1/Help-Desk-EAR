package help.desk.dao;

import java.util.List;
import help.desk.entities.User;
import help.desk.utils.UserRole;

public interface UserDAO {
	void addUser(User user);
    void editUser(User user);
    void deleteUser(User user);
    User findByUserId(Long userId);
    User findByUsername(String username);
    List<User> findByUserRole(UserRole role);
    List<User> findAllUsers();
}
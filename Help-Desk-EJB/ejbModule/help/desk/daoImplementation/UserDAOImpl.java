package help.desk.daoImplementation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import help.desk.dao.UserDAO;
import help.desk.entities.User;
import help.desk.utils.UserRole;

@Stateless
public class UserDAOImpl implements UserDAO {
   
	@PersistenceContext
    private EntityManager em;
	
	@Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public User findByUserId(Long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public List<User> findByUserRole(UserRole role) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);
        query.setParameter("role", role);
        return query.getResultList();
    }

    @Override
    public List<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    } 
}
package help.desk.daoImplementation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import help.desk.dao.TaskDAO;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.utils.TaskStatus;

@Stateless
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Task findTaskById(Long taskId) {
        return em.find(Task.class, taskId);
    }

    @Override
    public List<Task> findTasksByProject(Project project) {
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.project = :project", Task.class);
        query.setParameter("project", project);
        return query.getResultList();
    }

    @Override
    public List<Task> findTasksByStatus(TaskStatus status) {
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.status = :status", Task.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
    
    @Override
    public List<Task> findTasksByUser(User user) {
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.project.customer = :user OR :user MEMBER OF t.assignees", Task.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    
    @Override
    public List<Task> findAllTasks() {
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    @Override
    public void addTask(Task task) {
        em.persist(task);
    }

    @Override
    public void editTask(Task task) {
        em.merge(task);
    }

    @Override
    public void deleteTask(Task task) {
        em.remove(em.contains(task) ? task : em.merge(task));
    }
}
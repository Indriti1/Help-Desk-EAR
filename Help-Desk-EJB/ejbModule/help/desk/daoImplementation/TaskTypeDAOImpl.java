package help.desk.daoImplementation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

import help.desk.dao.TaskTypeDAO;
import help.desk.entities.TaskType;

@Stateless
public class TaskTypeDAOImpl implements TaskTypeDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addTaskType(TaskType taskType) {
        em.persist(taskType);
    }

    @Override
    public void editTaskType(TaskType taskType) {
        em.merge(taskType);
    }

    @Override
    public void deleteTaskType(TaskType taskType) {
        em.remove(em.contains(taskType) ? taskType : em.merge(taskType));
    }

    @Override
    public TaskType findByTaskTypeId(Long taskTypeId) {
    	return em.find(TaskType.class, taskTypeId);
    }

    @Override
    public TaskType findByTaskTypeName(String taskTypeName) {
        TypedQuery<TaskType> query = em.createQuery("SELECT tt FROM TaskType tt WHERE tt.name = :name", TaskType.class);
        query.setParameter("name", taskTypeName);
        List<TaskType> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<TaskType> findAllTaskTypes() {
        TypedQuery<TaskType> query = em.createQuery("SELECT tt FROM TaskType tt", TaskType.class);
        return query.getResultList();
    }
}
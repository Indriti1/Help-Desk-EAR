package help.desk.daoImplementation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import help.desk.dao.WorkLogDAO;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.entities.WorkLog;

@Stateless
public class WorkLogDAOImpl implements WorkLogDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addWorkLog(WorkLog workLog) {
        em.persist(workLog);
    }

    @Override
    public void editWorkLog(WorkLog workLog) {
        em.merge(workLog);
    }

    @Override
    public void deleteWorkLog(WorkLog workLog) {
        em.remove(em.contains(workLog) ? workLog : em.merge(workLog));
    }

    @Override
    public WorkLog findByWorkLogId(Long workLogId) {
        return em.find(WorkLog.class, workLogId);
    }

    @Override
    public List<WorkLog> findWorkLogsByEmployee(User employee) {
        TypedQuery<WorkLog> query = em.createQuery("SELECT wl FROM WorkLog wl WHERE wl.employee = :employee", WorkLog.class);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    @Override
    public List<WorkLog> findWorkLogsByTask(Task task) {
        TypedQuery<WorkLog> query = em.createQuery("SELECT wl FROM WorkLog wl WHERE wl.task = :task", WorkLog.class);
        query.setParameter("task", task);
        return query.getResultList();
    }

    @Override
    public List<WorkLog> findWorkLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        TypedQuery<WorkLog> query = em.createQuery("SELECT wl FROM WorkLog wl WHERE wl.date BETWEEN :start AND :end", WorkLog.class);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        return query.getResultList();
    }

    @Override
    public int getTotalHoursForTask(Long taskId) {
        TypedQuery<Integer> query = em.createQuery(
            "SELECT COALESCE(SUM(wl.hours), 0) FROM WorkLog wl WHERE wl.task.id = :taskId", Integer.class);
        query.setParameter("taskId", taskId);
        return query.getSingleResult();
    }
}
package help.desk.daoImplementation;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import help.desk.dao.ProjectDAO;
import help.desk.entities.Project;
import help.desk.entities.User;

@Stateless
public class ProjectDAOImpl implements ProjectDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Project findProjectById(Long projectId) {
        return em.find(Project.class, projectId);
    }

    @Override
    public List<Project> findAllProjects() {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        return query.getResultList();
    }
    
    @Override
    public List<Project> findProjectsByCustomerId(User customerId) {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.customer = :customer", Project.class);
        query.setParameter("customer", customerId);
        return query.getResultList();
    }

    @Override
    public void addProject(Project project) {
        em.persist(project);
    }

    @Override
    public void editProject(Project project) {
        em.merge(project);
    }

    @Override
    public void deleteProject(Project project) {
        em.remove(em.contains(project) ? project : em.merge(project));
    }
}
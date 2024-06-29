package help.desk.servicesImpl;

import java.util.List;
import help.desk.dao.ProjectDAO;
import help.desk.dao.UserDAO;
import help.desk.entities.Project;
import help.desk.entities.User;
import help.desk.services.ProjectService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ProjectServiceImpl implements ProjectService {
	
    @EJB
    private ProjectDAO projectDAO;
    
    @EJB
    private UserDAO userDAO; 

    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    @Override
    public void editProject(Project project) {
        projectDAO.editProject(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = projectDAO.findProjectById(projectId);
        if (project != null) {
            projectDAO.deleteProject(project);
        }
    }

    @Override
    public Project getProjectById(Long id) {
        return projectDAO.findProjectById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDAO.findAllProjects();
    }

    @Override
    public List<Project> getProjectsByCustomerId(Long customerId) {
        User customer = userDAO.findByUserId(customerId);
        return projectDAO.findProjectsByCustomerId(customer);
    }
}
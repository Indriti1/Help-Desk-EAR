package help.desk.managedbeans;

import help.desk.entities.Project;
import help.desk.entities.User;
import help.desk.services.ProjectService;
import help.desk.services.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProjectBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;
    
    @EJB
    private UserService userService;

    private Project newProject = new Project();
    private Project selectedProject;
    private Long selectedCustomerId;
    private List<Project> projects;
    private List<User> customers;
    
    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    public Long getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public List<Project> getProjects() {
    	projects = projectService.getAllProjects() ;
        return projects;
    }

    public List<User> getCustomers() {
    	customers = userService.getUsersByRole("CUSTOMER"); 
        return customers;
    }
    
    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void init() {
        projects = projectService.getAllProjects();
        customers = userService.getUsersByRole("CUSTOMER");
    }

    public String addProject() {
        User customer = userService.getUserById(getSelectedCustomerId());
        newProject.setCustomer(customer);
        projectService.addProject(getNewProject());
        newProject = new Project();
        selectedCustomerId = null;
        init();
        return "manageProjects?faces-redirect=true";
    }

    public void deleteProject(Project project) {
        projectService.deleteProject(project.getId());
        init();
    }
    
    public String prepareEdit(Project project) {
        this.selectedProject = project;
        this.selectedCustomerId = project.getCustomer().getId();
        return "editProject?faces-redirect=true&id=" + project.getId();
    }

    public String updateProject() {
        User customer = userService.getUserById(selectedCustomerId);
        selectedProject.setCustomer(customer);
        projectService.editProject(selectedProject);
        return "manageProjects?faces-redirect=true";
    }
}
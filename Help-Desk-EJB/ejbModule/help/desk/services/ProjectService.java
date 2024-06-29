package help.desk.services;

import java.util.List;
import help.desk.entities.Project;

public interface ProjectService {
    void addProject(Project project);
    void editProject(Project project);
    void deleteProject(Long projectId);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    List<Project> getProjectsByCustomerId(Long customerId);
}
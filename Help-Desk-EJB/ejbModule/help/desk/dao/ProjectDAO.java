package help.desk.dao;

import java.util.List;
import help.desk.entities.Project;
import help.desk.entities.User;

public interface ProjectDAO {
    Project findProjectById(Long projectId);
    List<Project> findAllProjects();
    List<Project> findProjectsByCustomerId(User customerId);
    void addProject(Project project);
    void editProject(Project project);
    void deleteProject(Project project);
}
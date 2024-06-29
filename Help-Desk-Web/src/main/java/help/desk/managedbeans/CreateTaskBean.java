package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.TaskType;
import help.desk.services.ProjectService;
import help.desk.services.TaskService;
import help.desk.services.TaskTypeService;
import help.desk.utils.TaskStatus;

@Named
@ViewScoped
public class CreateTaskBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;

    @EJB
    private TaskService taskService;

    @EJB
    private TaskTypeService taskTypeService;

    private Task task = new Task();
    private Long selectedProjectId;
    private Long selectedTaskTypeId;
    private List<Project> projects;
    private List<TaskType> taskTypes;
    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(Long selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public Long getSelectedTaskTypeId() {
        return selectedTaskTypeId;
    }

    public void setSelectedTaskTypeId(Long selectedTaskTypeId) {
        this.selectedTaskTypeId = selectedTaskTypeId;
    }

    public List<Project> getProjects() {
        projects = projectService.getAllProjects();
        return projects;
    }

    public List<TaskType> getTaskTypes() {
        taskTypes = taskTypeService.getAllTaskTypes();
        return taskTypes;
    }

    public void init() {
        projects = projectService.getAllProjects();
        taskTypes = taskTypeService.getAllTaskTypes();
    }

    public String createTask() {
        Project selectedProject = projectService.getProjectById(selectedProjectId);
        TaskType selectedTaskType = taskTypeService.getTaskTypeById(selectedTaskTypeId);
        task.setProject(selectedProject);
        task.setType(selectedTaskType);
        task.setStatus(TaskStatus.NEW);
        taskService.addTask(task);
        return "employeeDashboard?faces-redirect=true";
    }
}
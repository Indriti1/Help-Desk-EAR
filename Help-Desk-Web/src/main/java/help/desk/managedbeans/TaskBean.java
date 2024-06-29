package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import help.desk.entities.Task;
import help.desk.entities.Project;
import help.desk.entities.TaskType;
import help.desk.services.TaskService;
import help.desk.services.ProjectService;
import help.desk.services.TaskTypeService;
import help.desk.utils.TaskStatus;

@Named
@SessionScoped
public class TaskBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private TaskService taskService;

    @EJB
    private ProjectService projectService;

    @EJB
    private TaskTypeService taskTypeService;

    private Task newTask = new Task();
    private List<Task> tasks;
    private Task selectedTask;
    private Task selectedChangeTask;
    private Long selectedProjectId;
    private Long selectedTaskTypeId;
    
    public List<Task> getTasks() {
        if (tasks == null) {
            loadTasks();
        }
        return tasks;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
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
    
    public Task getSelectedTask() {
		return selectedTask;
	}
    
    public Task getSelectedChangeTask() {
		return selectedChangeTask;
	}
    
    public List<Project> getProjects() {
    	return projectService.getAllProjects();
    }

    public List<TaskType> getTaskTypes() {
        return taskTypeService.getAllTaskTypes(); 
    }

    private void loadTasks() {
        tasks = taskService.getAllTasks();
    }

    public void addTask() {
        Project selectedProject = projectService.getProjectById(selectedProjectId);
        TaskType selectedTaskType = taskTypeService.getTaskTypeById(selectedTaskTypeId);
        newTask.setProject(selectedProject);
        newTask.setType(selectedTaskType);
        taskService.addTask(newTask);
        newTask = new Task();
        selectedProjectId = null;
        selectedTaskTypeId = null;
        loadTasks();
    }
    
    public String prepareEdit(Task task) {
        this.selectedTask = task;
        this.selectedProjectId = task.getProject().getId();
        this.selectedTaskTypeId = task.getType().getId();
        return "editTask?faces-redirect=true&id=" + task.getId();
    }
    
    public String prepareStatusChange(Task task) {
        this.selectedChangeTask = task;
        return "changeTaskStatus?faces-redirect=true&id=" + task.getId();
    }
    
    public String updateTask() {
        Project project = projectService.getProjectById(selectedProjectId);
        TaskType taskType = taskTypeService.getTaskTypeById(selectedTaskTypeId);
        selectedTask.setProject(project);
        selectedTask.setType(taskType);
        taskService.editTask(selectedTask);
        return "manageTasks?faces-redirect=true";
    }
    
    public List<String> getStatuses() {
        return Arrays.asList("NEW", "IN_PROGRESS", "COMPLETED");
    }
    
    public List<String> getAvailableStatuses(Task task){
    	if(task.getStatus().equals(TaskStatus.NEW)) {
    		return Arrays.asList("IN_PROGRESS");
    	}
    	else {
    		return Arrays.asList("COMPLETED");
    	}
    }
    
    public String changeStatus() {
    	taskService.editTask(getSelectedChangeTask());
        return "employeeDashboard?faces-redirect=true";
    }

    public void editTask(Task task) {
        taskService.editTask(task);
        loadTasks();
    }

    public void deleteTask(Task task) {
        taskService.deleteTask(task.getId());
        loadTasks();
    }
}
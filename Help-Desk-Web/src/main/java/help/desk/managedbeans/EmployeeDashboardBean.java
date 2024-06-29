package help.desk.managedbeans;


import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.services.ProjectService;
import help.desk.services.TaskService;
import help.desk.services.WorkLogService;

@Named
@SessionScoped
public class EmployeeDashboardBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;

    @EJB
    private TaskService taskService;

    @EJB
    private WorkLogService workLogService;

    @Inject
    private SessionBean sessionBean;

    private List<Project> projects;
    private List<Task> allTasks;
    private Long selectedTaskId;
    private Task selectedTask;
    private Long selectedUserId;
    private Integer hoursWorked;
    
    public List<Project> getProjects() {
        projects = projectService.getAllProjects();
        return projects;
    }

    public List<Task> getAllTasks() {
        allTasks = taskService.getAllTasks() ; 
        return allTasks;
    }

    public Long getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(Long selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }
    
    public Long getSelectedUserId() {
    	return selectedUserId;
    }
    
    public void setSelectedUserId(Long selectedUserId) {
    	this.selectedUserId = selectedUserId;
    }
    
    public Task getSelectedTask() {
    	return selectedTask;
    }
    
    public void setSelectedTask(Task selectedTask) {
    	this.selectedTask = selectedTask;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setAllTasks(List<Task> allTasks) {
		this.allTasks = allTasks;
	}

    public void init() {
        projects = projectService.getAllProjects();
        allTasks = taskService.getAllTasks();
    }

    public void markTaskStatusChange(Task task, String status) {
        taskService.markTaskStatusChange(task.getId(), status);
        init(); // Refresh data
    }

    public String logHours() {
        workLogService.logWork(sessionBean.getLoggedInUser().getId(), selectedTaskId, hoursWorked);
        init(); // Refresh data
        hoursWorked = 0;
        return "employeeDashboard?faces-redirect=true";
    }
    
    public String prepareLogHours(Task task, User user) {
    	this.selectedTaskId = task.getId();
    	this.selectedTask = task;
        this.selectedUserId = user.getId();
        return "logHours?faces-redirect=true&user_id=" + user.getId() + "&task_id=" + task.getId();
    }
}
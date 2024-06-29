package help.desk.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import help.desk.dao.ProjectDAO;
import help.desk.dao.TaskDAO;
import help.desk.dao.UserDAO;
import help.desk.dao.WorkLogDAO;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.entities.WorkLog;
import help.desk.services.TaskService;
import help.desk.utils.ApproveStatus;
import help.desk.utils.TaskStatus;
import help.desk.utils.UserRole;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class TaskServiceImpl implements TaskService {
	
    @EJB
    private TaskDAO taskDAO;
    
    @EJB
    private ProjectDAO projectDAO;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private WorkLogDAO workLogDAO; 

    @Override
    public void addTask(Task task) {
    	task.setStatus(TaskStatus.NEW);
        taskDAO.addTask(task);
    }
    
    @Override
    public void editTask(Task task) {
    	taskDAO.editTask(task);
    }
    
    @Override
	public void deleteTask(Long taskId) {
    	Task task = taskDAO.findTaskById(taskId);
		if (task != null) {
	    	taskDAO.deleteTask(task);
		}
	}
 
    @Override
    public void updateTaskStatus(Long taskId, TaskStatus newStatus, String comment, Long userId) {
        Task task = taskDAO.findTaskById(taskId);
        User user = userDAO.findByUserId(userId);

        if (task != null && user != null) {
            if (user.getRole() == UserRole.EMPLOYEE) {
                if (newStatus == TaskStatus.IN_PROGRESS || newStatus == TaskStatus.COMPLETED) {
                    task.setStatus(newStatus);
                    task.getWorkLogs().add(new WorkLog(user, task, LocalDateTime.now(), 0, comment));
                    taskDAO.editTask(task);
                }
            } else if (user.getRole() == UserRole.CUSTOMER) {
                if (task.getStatus() == TaskStatus.COMPLETED) {
                    if (newStatus == TaskStatus.IN_PROGRESS) { // Reject completed task
                        task.setStatus(newStatus);
                        task.getWorkLogs().add(new WorkLog(user, task, LocalDateTime.now(), 0, "Task rejected: " + comment));
                        taskDAO.editTask(task);
                    } else if (newStatus == TaskStatus.COMPLETED) { // Approve completed task
                        task.setStatus(newStatus);
                        task.getWorkLogs().add(new WorkLog(user, task, LocalDateTime.now(), 0, "Task approved: " + comment));
                        taskDAO.editTask(task);
                    }
                }
            }
        }
    }
    
    @Override
	public List<Task> getAllTasks() {
		return taskDAO.findAllTasks();
	}

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
    	Project project = projectDAO.findProjectById(projectId);
        return taskDAO.findTasksByProject(project);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        User user = userDAO.findByUserId(userId);
        if (user.getRole() == UserRole.CUSTOMER) {
            return taskDAO.findTasksByUser(user);
        } else {
            return taskDAO.findAllTasks();
        }
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskDAO.findTaskById(taskId);
    }

	@Override
	public void markTaskStatusChange(Long taskId, String status) {
		Task task = taskDAO.findTaskById(taskId);
		task.setStatus(TaskStatus.valueOf(status));
		taskDAO.editTask(task);
	}

	@Override
	public void updateTaskStatus(Long taskId, String status, String approvedStatus, String comment) {
		Task task = taskDAO.findTaskById(taskId);
		if (task != null) {
	    	task.setStatus(TaskStatus.valueOf(status));
	    	task.setApproveStatus(ApproveStatus.valueOf(approvedStatus));
	    	task.getWorkLogs().add(new WorkLog(null, task, LocalDateTime.now(), 0, comment));
	    	taskDAO.editTask(task);
		}
	}

	@Override
	public int getTotalHoursWorked(Long id) {
        return workLogDAO.getTotalHoursForTask(id);
	}
}
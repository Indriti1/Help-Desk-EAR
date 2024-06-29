package help.desk.services;

import java.util.List;

import help.desk.entities.Task;
import help.desk.utils.TaskStatus;

public interface TaskService {
    void addTask(Task task);
    void editTask(Task task);
    void deleteTask(Long taskId);
    void updateTaskStatus(Long taskId, TaskStatus status, String comment, Long userId);
    List<Task> getAllTasks();
    List<Task> getTasksByProjectId(Long projectId);
    List<Task> getTasksByUserId(Long userId);
    Task getTaskById(Long taskId);
	void markTaskStatusChange(Long taskId, String status);
    void updateTaskStatus(Long taskId, String status, String approvedStatus, String comment);
	int getTotalHoursWorked(Long id);
}

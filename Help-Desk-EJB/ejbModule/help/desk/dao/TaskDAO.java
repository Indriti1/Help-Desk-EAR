package help.desk.dao;

import java.util.List;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.utils.TaskStatus;

public interface TaskDAO {
    Task findTaskById(Long taskId);
    List<Task> findTasksByProject(Project project);
    List<Task> findTasksByStatus(TaskStatus status);
    List<Task> findTasksByUser(User user);
    List<Task> findAllTasks();
    void addTask(Task task);
    void editTask(Task task);
    void deleteTask(Task task);
}

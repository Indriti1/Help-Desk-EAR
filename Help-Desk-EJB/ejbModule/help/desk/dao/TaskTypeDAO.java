package help.desk.dao;

import java.util.List;
import help.desk.entities.TaskType;

public interface TaskTypeDAO {
	void addTaskType(TaskType taskType);
    void editTaskType(TaskType taskType);
    void deleteTaskType(TaskType taskType);
    TaskType findByTaskTypeId(Long taskTypeId);
    TaskType findByTaskTypeName(String taskTypeName);
    List<TaskType> findAllTaskTypes();
}

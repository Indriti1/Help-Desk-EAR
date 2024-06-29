package help.desk.services;

import help.desk.entities.TaskType;
import java.util.List;

public interface TaskTypeService {
    TaskType addTaskType(TaskType taskType);
    TaskType editTaskType(TaskType taskType);
    void deleteTaskType(Long taskTypeId);
    TaskType getTaskTypeById(Long taskTypeId);
    TaskType getTaskTypeByName(String taskTypeName);
    List<TaskType> getAllTaskTypes();
}

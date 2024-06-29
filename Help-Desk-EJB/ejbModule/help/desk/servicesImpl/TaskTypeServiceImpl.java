package help.desk.servicesImpl;

import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import java.util.List;
import help.desk.dao.TaskTypeDAO;
import help.desk.entities.TaskType;
import help.desk.services.TaskTypeService;

@Stateless
public class TaskTypeServiceImpl implements TaskTypeService {

    @EJB
    private TaskTypeDAO taskTypeDAO;

    @Override
    public TaskType addTaskType(TaskType taskType) {
        taskTypeDAO.addTaskType(taskType);
        return taskType;
    }

    @Override
    public TaskType editTaskType(TaskType taskType) {
        taskTypeDAO.editTaskType(taskType);
        return taskType;
    }

    @Override
    public void deleteTaskType(Long taskTypeId) {
        TaskType taskType = taskTypeDAO.findByTaskTypeId(taskTypeId);
        if (taskType != null) {
            taskTypeDAO.deleteTaskType(taskType);
        }
    }

    @Override
    public TaskType getTaskTypeById(Long taskTypeid) {
        return taskTypeDAO.findByTaskTypeId(taskTypeid);
    }

    @Override
    public TaskType getTaskTypeByName(String taskTypeName) {
        return taskTypeDAO.findByTaskTypeName(taskTypeName);
    }

    @Override
    public List<TaskType> getAllTaskTypes() {
        return taskTypeDAO.findAllTaskTypes();
    }
}

package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Arrays;
import help.desk.entities.TaskType;
import help.desk.services.TaskTypeService;

@Named
@SessionScoped
public class TaskTypeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private TaskTypeService taskTypeService;

    private TaskType newTaskType = new TaskType();
    private List<TaskType> taskTypes;
    private List<String> predefinedTaskTypeNames = Arrays.asList("Bug", "Enhancement", "Upgrade");
    private TaskType selectedTaskType;
    
    public TaskType getNewTaskType() {
        return newTaskType;
    }

    public void setNewTaskType(TaskType newTaskType) {
        this.newTaskType = newTaskType;
    }

    public List<String> getPredefinedTaskTypeNames() {
        return predefinedTaskTypeNames;
    }
    
    public TaskType getSelectedTaskType() {
        return selectedTaskType;
    }
    
    public void setSelectedTaskType(TaskType selectedTaskType) {
        this.selectedTaskType = selectedTaskType;
    }

    public String addTaskType() {
        taskTypeService.addTaskType(getNewTaskType());
        loadTaskTypes();
        return "manageTaskTypes?faces-redirect=true";
    }

    public void editTaskType(TaskType taskType) {
        if (predefinedTaskTypeNames.contains(taskType.getName())) {
            taskTypeService.editTaskType(taskType);
            loadTaskTypes();
        }
    }

    public void deleteTaskType(TaskType taskType) {
        taskTypeService.deleteTaskType(taskType.getId());
        loadTaskTypes();
    }
    
    public String prepareEdit(TaskType taskType) {
        this.selectedTaskType = taskType;
        return "editTaskType?faces-redirect=true&id=" + taskType.getId();
    }

    public String updateTaskType() {
        taskTypeService.editTaskType(getSelectedTaskType());
        return "manageTaskTypes?faces-redirect=true";
    }

    public List<TaskType> getTaskTypes() {
        if (taskTypes == null) {
            loadTaskTypes();
        }
        return taskTypes;
    }

    private void loadTaskTypes() {
        taskTypes = taskTypeService.getAllTaskTypes();
    }
}
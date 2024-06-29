package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.services.ProjectService;
import help.desk.services.TaskService;
import help.desk.utils.ApproveStatus;

@Named
@ViewScoped
public class CustomerDashboardBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private ProjectService projectService;

    @EJB
    private TaskService taskService;

    @Inject
    private SessionBean sessionBean;

    private List<Project> projects;
    private Long selectedTaskId;
    private String rejectComment;
    private boolean showRejectForm;
    private Task selectedTask;
    
    public List<Project> getProjects() {
        projects = projectService.getProjectsByCustomerId(sessionBean.getLoggedInUser().getId());
        return projects;
    }

    public Long getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(Long selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }

    public String getRejectComment() {
        return rejectComment;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }
    
    public boolean getShowRejectForm() {
        return showRejectForm;
    }

    public void setShowRejectForm(boolean showRejectForm) {
        this.showRejectForm = showRejectForm;
    }

    public void init() {
        projects = getProjects();
    }

    public String approveTask(Task task) {
        taskService.updateTaskStatus(task.getId(), "COMPLETED", "APPROVED", "Task approved by customer");
        init(); // Refresh data
        return "customerDashboard?faces-redirect=true";
    }

    public void prepareReject(Task task) {
        this.selectedTask = task;
        this.showRejectForm = true;
        this.rejectComment = "";
    }

    public String rejectTask() {
        if (selectedTask != null) {
            taskService.updateTaskStatus(selectedTask.getId(), "IN_PROGRESS", "REJECTED", "Task rejected by customer: " + rejectComment);
            showRejectForm = false;
            selectedTask = null;
            rejectComment = null;
            init(); // Refresh data
        }
        return "customerDashboard?faces-redirect=true";
    }

    public void cancelReject() {
        showRejectForm = false;
        selectedTask = null;
        rejectComment = null;
    }
}
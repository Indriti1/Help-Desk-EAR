package help.desk.entities;

import jakarta.persistence.*;
import java.util.List;

import help.desk.utils.ApproveStatus;
import help.desk.utils.TaskStatus;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status; // NEW, IN_PROGRESS, COMPLETED
	
	@Enumerated(EnumType.STRING)
	private ApproveStatus approveStatus; // NEW, IN_PROGRESS, COMPLETED
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private TaskType type;
	
	@OneToMany(mappedBy = "task")
	private List<WorkLog> workLogs;
    
    public Task() {

	}
    
	public Task(String title, String description, TaskStatus status, Project project, TaskType type, ApproveStatus approveStatus) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.project = project;
		this.type = type;
		this.approveStatus = approveStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public ApproveStatus getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(ApproveStatus approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}

	public void setWorkLogs(List<WorkLog> workLogs) {
		this.workLogs = workLogs;
	}
}
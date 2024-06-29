package help.desk.dto;

import help.desk.utils.TaskStatus;

public class TaskDTO {
    private Long id;
    private String title;
    private TaskStatus status;
    private int totalHoursWorked;

    // Constructor
    public TaskDTO(Long id, String title, TaskStatus status, int totalHoursWorked) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.totalHoursWorked = totalHoursWorked;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(int totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}
}
package help.desk.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class WorkLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User employee;
    @ManyToOne
    private Task task;
    private LocalDateTime date;
    private int hours;
    private String comment;
	
    public WorkLog() {
	}
    
    public WorkLog(User employee, Task task, LocalDateTime date, int hours, String comment) {
		super();
		this.employee = employee;
		this.task = task;
		this.date = date;
		this.hours = hours;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    
    
    	
    
    
    
    
}
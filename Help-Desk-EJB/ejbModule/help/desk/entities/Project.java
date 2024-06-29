package help.desk.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Task> tasks;
	
    public Project() {
    	
    }
    
    public Project( String name, List<Task> tasks) {
		this.name = name;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
    
    public User getCustomer() {
		return customer;
	}
    
    public void setCustomer(User customer) {
		this.customer = customer;
	}
    
    @Override
	public String toString() {
		return getName();
	}
}
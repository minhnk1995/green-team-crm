package cybersoft.javabackend.crm.model;

import java.time.LocalDateTime;

public class Job {
	private int id;
	private String name;
	private String description;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private User manager;
	
	public Job() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}
	public LocalDateTime getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	public User getManager() {
		return manager;
	}
	public void setManagerID(User manager) {
		this.manager = manager;
	}
	
}
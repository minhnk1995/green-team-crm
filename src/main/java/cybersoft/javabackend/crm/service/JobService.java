package cybersoft.javabackend.crm.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.repository.JobRepository;

public class JobService {
	private JobRepository jobRepo;
	
	
	public JobService() {
		jobRepo = new JobRepository();
	}


	public List<Job> getAllJobsByUserID(int userID){
		return jobRepo.getAllJobByUserID(userID);
	}
	
	public List<Job> getAllJobsByManagerID(int userID){
		return jobRepo.getAllJobsByManagerID(userID);
	}
	
	public boolean validatePeriod(String start, String end) {
		LocalDateTime startDate, endDate;

		startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		return LocalDateTime.now().minusDays(1).isAfter(startDate) || LocalDateTime.now().isAfter(endDate);
	}


	public boolean checkNameExist(String name) {
		if(jobRepo.checkNameExist(name).equals(0))
			return false;
		return true;
	}


	public boolean addJob(String name, String discription, String start, String end, int managerID) {
		return jobRepo.addJob(name, discription, start, end, managerID);
	}


	public Job getJobByID(int jobID) {
		return jobRepo.getJobByID(jobID);
	}


	public List<Task> getAllTasksByJobID(int id) {
		return jobRepo.getAllTasksByJobID(id);
	}


	public List<Task> getTasksByUserIdAndJobId(int userId, int jobId) {
		return jobRepo.getTasksByUserIdAndJobId(userId, jobId);
	}
}

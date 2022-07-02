package cybersoft.javabackend.crm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.repository.JobRepository;
import cybersoft.javabackend.crm.util.ComConst;

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
		LocalDate startDate, endDate;

		startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(startDate + " " + endDate);
		return LocalDate.now().minusDays(1).isBefore(startDate) && LocalDate.now().isBefore(endDate);
	}


	public boolean checkNameExist(String name) {
		if(jobRepo.checkNameExist(name).equals(0))
			return false;
		return true;
	}


	public boolean addJob(String name, String description, String start, String end, int managerID) {
		return jobRepo.addJob(name, description, start, end, managerID);
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


	public boolean deleteProjectById(int jobID) {
		return jobRepo.deleteProjectById(jobID) && jobRepo.deleteTasksByProjectId(jobID);
	}


	public boolean editJobById(int jobId, String name, String description, String start, String end, int managerID) {
		return jobRepo.editJobById(jobId, name, description, start, end, managerID);
	}


	public boolean validateDeadline(String start, String end) {
		LocalDate startDate, endDate;

		startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(startDate + " " + endDate);
		return startDate.isBefore(endDate) && LocalDate.now().isBefore(endDate);
	}


	public List<Job> getAllJobs() {
		return jobRepo.getAllJob();
	}
	
	public void updateTaskStatus() {
		List<Task> lstTasks = new ArrayList<>();
		
		lstTasks.addAll(jobRepo.getAllTasks());
		for(Task item : lstTasks) {
			switch(item.getStatus().getId()) {
			case ComConst.STATUS_CHUATHUCHIEN:
				if(LocalDate.now().plusDays(1).isAfter(item.getStart_date().toLocalDate())) {
					jobRepo.updateTaskStatusToTwo(item.getId());
				}
				break;
			case ComConst.STATUS_DANGTHUCHIEN:
				if(LocalDate.now().isAfter(item.getEnd_date().toLocalDate())) {
					jobRepo.updateTaskStatusToThree(item.getId());
				}
				else if (LocalDate.now().isBefore(item.getStart_date().toLocalDate())) {
					jobRepo.updateTaskStatusToOne(item.getId());
				}
				break;
			}
		}
	}


	public Task getTaskById(int taskId) {
		return jobRepo.getTaskById(taskId);
	}


	public boolean addTask(String name, String start, String end, int userId, int jobID, int status) {
		return jobRepo.addTask(name, start, end, userId, jobID, status);
	}


	public boolean editTaskById(int taskId, String name, String start, String end, int userId, int status) {
		return jobRepo.editTaskById(taskId, name, start, end, userId, status);
	}


	public boolean deleteTaskById(int taskId) {
		return jobRepo.deleteTaskById(taskId);
	}


	public int getJobIdByTask(int taskId) {
		return jobRepo.getJobIdByTask(taskId);
	}


	public void commitTask(int taskId) {
		jobRepo.updateTaskStatusToThree(taskId);;
		
	}


	public boolean validatePeriodTask(String start, String end, String jobStart, String jobEnd) {
		LocalDate startDate, endDate, jobStartDate, jobEndDate;

		startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		jobStartDate = LocalDate.parse(jobStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		jobEndDate = LocalDate.parse(jobEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return startDate.plusDays(1).isAfter(jobStartDate) && endDate.minusDays(1).isBefore(jobEndDate);
	}


	public boolean validateDeadlineTask(String start, String end, String jobEnd) {
		LocalDate startDate, endDate, jobEndDate;

		startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		jobEndDate = LocalDate.parse(jobEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return endDate.plusDays(1).isAfter(startDate) && endDate.minusDays(1).isBefore(jobEndDate);
	}


	public List<Job> getAllJobWithoutManager() {
		return jobRepo.getAllJobWithoutManager();
	}
}

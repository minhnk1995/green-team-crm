package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.JobService;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.ComConst;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet({UrlConst.TASK_CREATE, UrlConst.TASK_EDIT, UrlConst.TASK_REMOVE, UrlConst.TASK_DETAIL, UrlConst.TASK_COMMIT})
public class TaskServlet extends HttpServlet {
	private JobService jobService;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		jobService = new JobService();
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Task task = new Task();
		String jobID;
		String path = req.getServletPath();
		List<UpdateUserDto> lstUser = new ArrayList<>();
		
		lstUser.addAll(userService.getUserDtoByRole(ComConst.ROLE_USER));
		lstUser.addAll(userService.getUserDtoByRole(ComConst.ROLE_MANAGER));
		req.setAttribute("lstUser", lstUser);
		switch(path)
		{
		case UrlConst.TASK_CREATE:
			jobID = req.getParameter("jobID");
			Job job = new Job();
			job = jobService.getJobByID(Integer.parseInt(jobID));
			req.setAttribute("job", job);
			req.setAttribute("jobID", jobID);
			req.getRequestDispatcher(JspConst.TASK_CREATE).forward(req, resp);
			break;
		case UrlConst.TASK_DETAIL:
			jobID = req.getParameter("jobID");
			req.setAttribute("jobID", jobID);
			int taskId = Integer.parseInt(req.getParameter("task"));
			task = jobService.getTaskById(taskId);
			req.setAttribute("task", task);
			req.getRequestDispatcher(JspConst.TASK_DETAIL).forward(req, resp);
			break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name, start, end;
		int userId, jobID, taskId, status;
		List<UpdateUserDto> lstUser = new ArrayList<>();
		boolean flag = true;
		Job job;
		Task task;
		String path = req.getServletPath();
		lstUser.addAll(userService.getUserDtoByRole(ComConst.ROLE_USER));
		
		switch (path) {
		case UrlConst.TASK_CREATE:
			String jobStart, jobEnd;
			job = new Job();

			jobID = Integer.parseInt(req.getParameter("jobID"));
			job= jobService.getJobByID(jobID);
			name = req.getParameter("name");
			jobStart = job.getStart_date().toString().substring(0, 10);
			jobEnd = job.getEnd_date().toString().substring(0, 10);
			start = req.getParameter("period").substring(0, 10).replace("/", "-");
			end = req.getParameter("period").substring(14).replace("/", "-");
			userId = Integer.parseInt(req.getParameter("user"));

			req.setAttribute("lstUser", lstUser);

			if (!jobService.validatePeriodTask(start, end, jobStart, jobEnd)) {
				flag = false;
				req.setAttribute("invalidPeriod", "Please choose Period in future!");
			}
			if (!jobService.validateDeadlineTask(start, end, jobEnd)) {
				flag = false;
				req.setAttribute("invalidPeriod", "Please choose Period in future!");
			}
			if (jobService.checkNameExist(name)) {
				flag = false;
				req.setAttribute("invalidName", "Task name has already existed");
			} else if (name.equals("")) {
				flag = false;
				req.setAttribute("blankName", "Task name couldn't be blank");
			}
			if (userId == 0) {
				flag = false;
				req.setAttribute("invalidManager", "Please assign to a staff");

			}
			if(LocalDate.now().isBefore(LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
				status = 1;
			}
			else if(LocalDate.now().isAfter(LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
				status = 3;
			}
			else {
				status = 2;
			}
			req.setAttribute("jobID", jobID);
			if (flag) {
				flag = jobService.addTask(name, start, end, userId, jobID, status);
				if (flag)
					resp.sendRedirect(req.getContextPath() + UrlConst.JOB_DETAIL + "?job=" + jobID);
				else {
					req.setAttribute("unable", "Unable to create new project!");
					req.getRequestDispatcher(JspConst.TASK_CREATE).forward(req, resp);
				}
			} else {
				req.setAttribute("job", job);
				req.getRequestDispatcher(JspConst.TASK_CREATE).forward(req, resp);
			}
			break;
		case UrlConst.TASK_REMOVE:
			taskId = Integer.parseInt(req.getParameter("taskID"));
			jobID = jobService.getJobIdByTask(taskId);
			flag = jobService.deleteTaskById(taskId);
			if (flag) {
				req.setAttribute("msg", "Successfully remove the task!");
				req.setAttribute("taskRemoveSuccess", true);
				req.setAttribute("jobID", jobID);
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			else {
				req.setAttribute("msg", "Failed to remove the task!");
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			break;
		case UrlConst.TASK_EDIT:
			job = new Job();
			taskId = Integer.parseInt(req.getParameter("taskID"));
			jobID = jobService.getJobIdByTask(taskId);
			task = jobService.getTaskById(taskId);
			name = req.getParameter("updated-name");
			String dateStatus = req.getParameter("dateStatus");
			
			if (dateStatus.equals("1")) {
				start = req.getParameter("jobStart").substring(0, 10);
				end = req.getParameter("updated-deadline").substring(0, 10).replace("/", "-");
				if (!jobService.validateDeadline(start, end)) {
					flag = false;
					req.setAttribute("invalidPeriod", "Please choose Period in future!");
				}
			} else {
				start = req.getParameter("updated-period").substring(0, 10).replace("/", "-");
				end = req.getParameter("updated-period").substring(14).replace("/", "-");
				if (!jobService.validatePeriod(start, end)) {
					flag = false;
					req.setAttribute("invalidPeriod", "Please choose Period in future!");
				}
			}
			userId = Integer.parseInt(req.getParameter("updated-user"));

			req.setAttribute("lstUser", lstUser);

			if(jobID==-1) {
				flag = false;				
			}
			else {
				req.setAttribute("jobID", jobID);				
			}
			if (name.equals("")) {
				flag = false;
				req.setAttribute("blankName", "Task name couldn't be blank");
			}
			if (userId == 0) {
				flag = false;
				req.setAttribute("invalidManager", "Please assign to a staff");

			}
			
			if(LocalDateTime.now().isBefore(task.getStart_date())) {
				status = 1;
			}
			else if(LocalDateTime.now().isAfter(task.getEnd_date())) {
				status = 3;
			}
			else {
				status = 2;
			}
			
			req.setAttribute("jobID", jobID);
			
			if (flag) {	
				flag = jobService.editTaskById(taskId, name, start, end, userId, status);			
				if (flag) {
					req.setAttribute("msg", "Successfully edit the task!");
					req.setAttribute("taskRemoveSuccess", true);
					req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
				} else {
					req.setAttribute("msg", "Failed to edit the task!");
					req.setAttribute("taskRemoveSuccess", true);
					req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
				}
			} else {
				req.setAttribute("msg", "Failed to edit the task!");
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			break;
		case UrlConst.TASK_COMMIT:
			taskId = Integer.parseInt(req.getParameter("taskID"));
			jobID = jobService.getJobIdByTask(taskId);
			jobService.commitTask(taskId);
			resp.sendRedirect(req.getContextPath() + UrlConst.JOB_DETAIL + "?job=" + jobID);
			break;			
		}
	}
}
	

package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
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

import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.JobService;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.ComConst;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "jobServlet", urlPatterns = { UrlConst.JOB_CREATE, UrlConst.JOB_DETAIL, UrlConst.JOB_REMOVE,
		UrlConst.JOB_EDIT })
public class JobServlet extends HttpServlet {
	User user;
	List<User> lstManager;
	private JobService jobService;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		jobService = new JobService();
		userService = new UserService();
		lstManager = new ArrayList<>();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession();

		lstManager = userService.getListUserByRole(ComConst.ROLE_MANAGER);
		user = (User) session.getAttribute("user");
		switch (path) {
		case UrlConst.JOB_CREATE:
			req.setAttribute("lstManager", lstManager);
			req.getRequestDispatcher(JspConst.JOB_CREATE).forward(req, resp);
			break;
		case UrlConst.JOB_DETAIL:
			Job job;
			int jobID = Integer.parseInt(req.getParameter("job"));
			job = jobService.getJobByID(jobID);
			req.setAttribute("lstManager", lstManager);
			if (job != null) {
				List<Task> lstTask = new ArrayList<>();

				if (user.getRole().getId() == ComConst.ROLE_USER) {
					lstTask.addAll(jobService.getTasksByUserIdAndJobId(user.getId(), job.getId()));
				} else {
					lstTask.addAll(jobService.getAllTasksByJobID(job.getId()));
				}
				req.setAttribute("lstTask", lstTask);
				req.setAttribute("job", job);
				req.getRequestDispatcher(JspConst.JOB_DETAIL).forward(req, resp);
			}
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name, description, start, end;
		int managerID;
		boolean flag = true;
		String path = req.getServletPath();

		switch (path) {
		case UrlConst.JOB_CREATE:
			name = req.getParameter("name");
			description = req.getParameter("descrpt");
			start = req.getParameter("period").substring(0, 10).replace("/", "-");
			end = req.getParameter("period").substring(14).replace("/", "-");
			managerID = Integer.parseInt(req.getParameter("manager"));

			req.setAttribute("lstManager", lstManager);

			if (!jobService.validatePeriod(start, end)) {
				flag = false;
				req.setAttribute("invalidPeriod", "Please choose Period in future!");
			}
			if (jobService.checkNameExist(name)) {
				flag = false;
				req.setAttribute("invalidName", "Project name has already existed");
			} else if (name.equals("")) {
				flag = false;
				req.setAttribute("blankName", "Project name couldn't be blank");
			}
			if (managerID == 0) {
				flag = false;
				req.setAttribute("invalidManager", "Please choose a Manager");

			}
			if (flag) {
				flag = jobService.addJob(name, description, start, end, managerID);
				if (flag)
					resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
				else {
					req.setAttribute("unable", "Unable to create new project!");
					req.getRequestDispatcher(JspConst.JOB_CREATE).forward(req, resp);
				}
			} else {
				req.getRequestDispatcher(JspConst.JOB_CREATE).forward(req, resp);
			}
			break;
		case UrlConst.JOB_REMOVE:
			int jobID = Integer.parseInt(req.getParameter("jobID"));
			System.out.println(jobID);
			flag = jobService.deleteProjectById(jobID);
			System.out.println(flag);
			
			if (flag) {
				req.setAttribute("msg", "Successfully remove the project!");
				req.setAttribute("projectRemoveSuccess", true);
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			else {
				req.setAttribute("msg", "Failed to remove the project!");
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			break;
		case UrlConst.JOB_EDIT:
			name = req.getParameter("updated-name");
			description = req.getParameter("updated-descrpt");
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
			managerID = Integer.parseInt(req.getParameter("updated-manager"));

			req.setAttribute("lstManager", lstManager);

			
			if (name.equals("")) {
				flag = false;
				req.setAttribute("blankName", "Project name couldn't be blank");
			}
			if (managerID == 0) {
				flag = false;
				req.setAttribute("invalidManager", "Please choose a Manager");

			}
			
			int jobId = Integer.parseInt(req.getParameter("jobID"));
			req.setAttribute("jobID", jobId);
			
			if (flag) {	
				flag = jobService.editJobById(jobId, name, description, start, end, managerID);			
				if (flag) {
					req.setAttribute("msg", "Successfully edit the project!");
					req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
				} else {
					req.setAttribute("msg", "Failed to edit the project!");
					req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
				}
			} else {
				req.setAttribute("msg", "Failed to edit the project!");
				req.getRequestDispatcher(JspConst.JOB_RESULT).forward(req, resp);
			}
			break;
		}

	}
}
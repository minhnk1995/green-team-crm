package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.JobService;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.ComConst;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "homeServlet", urlPatterns = UrlConst.HOME)
public class HomeServlet extends HttpServlet {
	User user;
	private JobService jobService;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		jobService = new JobService();
		userService = new UserService();
		jobService.updateTaskStatus();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Job> lstJob = new ArrayList<>();
		User user = new User();
		HttpSession session = req.getSession();
		user = (User) session.getAttribute("user");

		lstJob = jobService.getAllJobsByUserID(user.getId());
		if(user.getRole().getId()==ComConst.ROLE_MANAGER) {
			lstJob.addAll(jobService.getAllJobsByManagerID(user.getId()));
		}
		else if(user.getRole().getId()==ComConst.ROLE_ADMIN) {
			lstJob.addAll(jobService.getAllJobs());
		}
		List<Integer> numUser;
		if(lstJob!=null) {
			Collections.reverse(lstJob);
			numUser = new ArrayList<>();
			for (Job job : lstJob) {
				numUser.add(userService.countUserByJob(job.getId()));
			}
			req.setAttribute("numUser", numUser);
		}
		req.setAttribute("lstJob", lstJob);
		req.setAttribute("user", user);
		req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
	}
}

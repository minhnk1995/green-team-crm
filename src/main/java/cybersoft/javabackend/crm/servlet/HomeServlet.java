package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.service.UserServiceImpl;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "homeServlet", urlPatterns = { UrlConst.HOME, UrlConst.LOGIN })
public class HomeServlet extends HttpServlet {
	private UserService service;
	private List<User> lstUser;

	@Override
	public void init() throws ServletException {
		lstUser = new ArrayList<>();
		service = new UserServiceImpl();
		lstUser = service.getAllUsers();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case UrlConst.LOGIN:
			req.getRequestDispatcher(JspConst.LOGIN).forward(req, resp);
			break;
		case UrlConst.HOME:
			req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case UrlConst.LOGIN:
			String username, password;

			username = req.getParameter("email");
			password = req.getParameter("password");

			Optional<User> currentUser = lstUser.stream().filter(t -> t.getEmail().equals(username))
					.filter(t -> t.getPassword().equals(password)).findFirst();

			if (currentUser.isPresent()) {
				HttpSession session = req.getSession();
				session.setAttribute("user", currentUser.get());
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			} else {
				req.setAttribute("wronglogin", true);
				req.getRequestDispatcher(JspConst.LOGIN).forward(req, resp);
			}
			break;
		}
	}
}

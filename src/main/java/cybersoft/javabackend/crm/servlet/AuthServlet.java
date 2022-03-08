package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.AuthService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "authServlet", urlPatterns = { UrlConst.AUTH_LOGIN })
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AuthService authService;

	@Override
	public void init() throws ServletException {
		authService = new AuthService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case UrlConst.AUTH_LOGIN:
			req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);

			break;

		case UrlConst.AUTH_OUT:
			resp.sendRedirect(req.getContextPath() + JspConst.AUTH_LOGIN);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		String email, password;

		email = req.getParameter("email");
		password = req.getParameter("password");
		Optional<User> user = authService.login(email, password);
		if (user.isPresent()) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
		} else {
			req.setAttribute("wronglogin", true);
			req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);
		}
	}
}

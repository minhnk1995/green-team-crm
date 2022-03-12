package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.AuthService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "authServlet", urlPatterns = { UrlConst.AUTH_LOGIN, UrlConst.AUTH_OUT })
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
			HttpSession session=req.getSession();  
            session.invalidate();  
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		String email, password, check;

		email = req.getParameter("email");
		password = req.getParameter("password");
		check = req.getParameter("remember");
		Optional<User> user = authService.login(email, password);
		if (user.isPresent()) {
			HttpSession session = req.getSession();
			Cookie cookieEmail = new Cookie("email", user.get().getEmail());
			Cookie cookiePass = new Cookie("pass", user.get().getPassword());

			session.setAttribute("user", user.get());
			
			if (req.getParameter("remember") == null) {				
				cookieEmail.setMaxAge(0);
				cookiePass.setMaxAge(0);
			}
			resp.addCookie(cookieEmail);
			resp.addCookie(cookiePass);

			resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
		} else {
			req.setAttribute("wronglogin", true);
			req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);
		}
	}
}

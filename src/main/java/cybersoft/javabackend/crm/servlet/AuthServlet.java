package cybersoft.javabackend.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name="authServlet",urlPatterns = {
		UrlConst.AUTH_LOGIN
		})
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		
		switch(path) {
		case UrlConst.AUTH_LOGIN:
			req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);
			
			break;
			
		case UrlConst.AUTH_OUT:
			resp.sendRedirect( req.getContextPath() + JspConst.AUTH_LOGIN);
			break;
		}
		
		
		
	}
}

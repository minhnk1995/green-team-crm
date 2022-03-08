package cybersoft.javabackend.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.util.UrlConst;

@WebFilter(filterName = "authFilter", urlPatterns = UrlConst.GLOBAL)
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();
		Object user = req.getSession().getAttribute("user");
		
		if(!path.startsWith(UrlConst.LOGIN)) {
			
			if(user == null) {
				resp.sendRedirect(req.getContextPath() + UrlConst.LOGIN);
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			if(user == null) {
				chain.doFilter(request, response);
			}
			else {
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			}
		}
	}

}

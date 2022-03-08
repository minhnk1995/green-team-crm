package cybersoft.javabackend.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = { UrlConst.USER_PROFILE, UrlConst.USER_ADD, UrlConst.USER_DASHBOARD,
		UrlConst.USER_DELETE, UrlConst.USER_UPDATE, })
public class UserServlet extends HttpServlet {
	private UserService service;

	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		service = new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {

		// danh sách
		case UrlConst.USER_DASHBOARD:
			// view dashboard
			break;
		// thêm nhân viên
		case UrlConst.USER_ADD:
			// view
			break;
		// xóa nhân viên
		case UrlConst.USER_DELETE:
			// view
			break;
		// thông tin nhân viên
		case UrlConst.USER_PROFILE:
			// view
			break;

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {

		// danh sách
		case UrlConst.USER_DASHBOARD:

			break;
		// thêm nhân viên
		case UrlConst.USER_ADD:
			addUserPost(req,resp);
			break;
		// xóa nhân viên
		case UrlConst.USER_DELETE:

			break;
		// thông tin nhân viên
		case UrlConst.USER_PROFILE:

			break;

		}
	}

	private void addUserPost(HttpServletRequest req, HttpServletResponse resp) {
		UserDto userDto = createUserDto(req,resp);
		service.add(userDto);
		
		//code them
		
	}

	private UserDto createUserDto(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserDto(email,password,name,phone,address,roleId);
	}

}

package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserDto;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = { 
		UrlConst.USER_PROFILE, UrlConst.USER_ADD, UrlConst.USER_DASHBOARD,
		UrlConst.USER_DELETE, UrlConst.USER_UPDATE })
public class UserServlet extends HttpServlet {
	private UserService service;

	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		service = new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id;
		switch (req.getServletPath()) {

		// danh sách
		case UrlConst.USER_DASHBOARD:
			List<User> users = service.getAllUser();
			req.setAttribute("users", users);
			req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);;
			// view dashboard
			break;
		// thêm nhân viên
		case UrlConst.USER_ADD:
			req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
			// view
			break;
		// xóa nhân viên
		case UrlConst.USER_DELETE:
			id = Integer.parseInt(req.getParameter("id"));
			User curentUser = service.getUserById(id);
			req.setAttribute("curentUser", curentUser);
			req.getRequestDispatcher(JspConst.USER_DELETE).forward(req, resp);
			// view
			break;
		// thông tin nhân viên
		case UrlConst.USER_PROFILE:
			id = Integer.parseInt(req.getParameter("id"));
			User userProfile = service.getUserById(id);
			req.setAttribute("userProfile", userProfile);
			req.getRequestDispatcher(JspConst.USER_PROFILE).forward(req, resp);
			// view
			break;
		case UrlConst.USER_UPDATE:
			id = Integer.parseInt(req.getParameter("id"));
			User userUpdate = service.getUserById(id);
			req.setAttribute("userUpdate", userUpdate);
			req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
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
			req.getRequestDispatcher(JspConst.RESULT_INSERT).forward(req, resp);
			break;
		// xóa nhân viên
		case UrlConst.USER_DELETE:

			break;
		// thông tin nhân viên
		case UrlConst.USER_PROFILE:

			break;
		case UrlConst.USER_UPDATE:
			UpdateUserDto user = getUserUpdateDto(req,resp);
			if(user == null) {
				User userCurrentUpdate = service.getUserById(Integer.parseInt(req.getParameter("id")));
				req.setAttribute("userUpdate", userCurrentUpdate);
				req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
				break;
			}
			boolean resultUpdate = service.updateUser(user);
			if(resultUpdate) {
				resp.sendRedirect(req.getContextPath() +UrlConst.USER_PROFILE+"?id=" +user.getId());
			}else {
				resp.sendRedirect(req.getContextPath() +UrlConst.USER_UPDATE+"?id=" +user.getId());
			}
			break;
		}
	}

	private UpdateUserDto getUserUpdateDto(HttpServletRequest req, HttpServletResponse resp) {
		int id =Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		String repeatPassword = req.getParameter("repeatPassword");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		boolean validPassword = password.equalsIgnoreCase(repeatPassword);
		if(!validPassword) {
			req.setAttribute("lastPassword", password);
			req.setAttribute("lastRPassword", repeatPassword);
			req.setAttribute("validPassword", !validPassword);
			return null;
		}
		return new UpdateUserDto(id,name, repeatPassword, email, phone, address,roleId);
	}
	private void addUserPost(HttpServletRequest req, HttpServletResponse resp) {
		UserDto userDto = createUserDto(req,resp);
		boolean insertStatus = service.add(userDto);
		req.setAttribute("insertStatus", insertStatus);
	}

	private UserDto createUserDto(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		System.out.println(email + " " + password + " " + name + " " + phone + " " + address + " " + roleId);
		return new UserDto(email,password,name,phone,address,roleId);
	}

}

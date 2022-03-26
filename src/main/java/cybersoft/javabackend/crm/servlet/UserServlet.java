package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import cybersoft.javabackend.crm.dto.UpdateUserDto;
import cybersoft.javabackend.crm.dto.UserCreatedDto;
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
		User user = new User();
		HttpSession session = req.getSession();
		user = (User) session.getAttribute("user");
		
		switch (req.getServletPath()) {

		// danh sách
		case UrlConst.USER_DASHBOARD:
			List<User> users = service.getAllUser();
			req.setAttribute("users", users);
			req.setAttribute("curUser", user);
			req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);;
			// view dashboard
			break;
		// thêm nhân viên
		case UrlConst.USER_ADD:
			req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
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
		req.setCharacterEncoding("UTF-8");
		int id;
		switch (req.getServletPath()) {

		// danh sách
		case UrlConst.USER_DASHBOARD:

			break;
		// thêm nhân viên
		case UrlConst.USER_ADD:
			if(!addUserPost(req,resp)) {
				req.setAttribute("createFailToastr", "Thêm nhân viên thất bại,Xin thử lại!!");
				req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
				break;	
			}
			req.setAttribute("createSuccessToastr", "Thêm nhân viên thành công!!");
			req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
//			resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			break;
		// xóa nhân viên
		case UrlConst.USER_DELETE:
			id = Integer.parseInt(req.getParameter("id"));
			if(!service.deleteById(id)) {
				resp.sendRedirect(req.getContextPath() +UrlConst.USER_PROFILE+"?id=" + id);
			}else {
				resp.sendRedirect(req.getContextPath() +UrlConst.USER_DASHBOARD);
			}
			break;
		// thông tin nhân viên
		case UrlConst.USER_PROFILE:

			break;
		case UrlConst.USER_UPDATE:
			id = Integer.parseInt(req.getParameter("id"));
			boolean resultUpdate = updateUser(req,resp) ;
			if(!resultUpdate) {
				req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
//				resp.sendRedirect(req.getContextPath() +UrlConst.USER_PROFILE+"?id=" +id);
				break;
			}
			resp.sendRedirect(req.getContextPath() +UrlConst.USER_PROFILE+"?id=" +id);
			break;
		}
	}

	
	private boolean updateUser(HttpServletRequest req, HttpServletResponse resp) {
		UpdateUserDto userDto = getUserUpdateDto(req,resp);
		req.setAttribute("userUpdate", userDto);
		boolean userExists = service.checkUserExistByName(userDto.getName());
		req.setAttribute("lastPassword", userDto.getPassword());
		req.setAttribute("lastRPassword", userDto.getrPassword());
		if(userExists) {
			req.setAttribute("userExists", userExists);
			return false;
		}
		if(!userDto.getPassword().equals(userDto.getrPassword())) {

			req.setAttribute("validPassword", true);
			return false;
		}
		return service.updateUser(userDto);
	}
	private UpdateUserDto getUserUpdateDto(HttpServletRequest req, HttpServletResponse resp) {
		int id =Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		String rPassword = req.getParameter("repeatPassword");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		return new UpdateUserDto(id,name, password,rPassword, email, phone, address,roleId);
	}
	private boolean addUserPost(HttpServletRequest req, HttpServletResponse resp) {
		UserCreatedDto userDto = createUserDto(req,resp);
		req.setAttribute("lastUserDto", userDto);
		boolean userExists = service.checkUserExistByName(userDto.getName());
		if(userExists) {
			req.setAttribute("userExists", userExists);
			return false;
		}
		if(!userDto.getPassword().equals(userDto.getrPassword())) {
			req.setAttribute("validPassword", true);
			return false;
		}
		return service.add(userDto);
	
		
	}

	private UserCreatedDto createUserDto(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String rPassword = req.getParameter("rPassword");
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		System.out.println(email + " " + password + "pre " + rPassword + " " + name + " " + phone + " " + address + " " + roleId);
	
		return new UserCreatedDto(email,password,rPassword,name,phone,address,roleId);
	}

}

package controller.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import service.IUserService;
import service.imp.UserService;
import utils.SessionUtil;

@WebServlet(urlPatterns = { "/dangnhap", "/dangky" })
public class LoginRegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IUserService userService = UserService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/dangnhap")) {
			url = "view/login.jsp";
		} else if (url.startsWith(request.getContextPath() + "/dangky")) {
			url = "view/register.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		UserModel model = new UserModel();
		boolean isUseSendRedirect = false;
		
		try {
			if (request.getParameter("id") != null)
				model.setId(Long.parseLong(request.getParameter("id")));
		} catch (Exception e) {
			model.setId(0L);
		}

		model.setFullName(request.getParameter("fullname"));
		model.setStatus(request.getParameter("status"));
		model.setEmail(request.getParameter("email"));
		model.setPassword(request.getParameter("password"));

		Map<String, String> map = null;
		if (option.equalsIgnoreCase("register")) {
			map = userService.insert(model);
		} else if (option.equalsIgnoreCase("login")) {
			if (userService.findOneByEmailAndPassword(model.getEmail(), model.getPassword()) != null) {
				model = userService.findOneByEmailAndPassword(model.getEmail(), model.getPassword());
				isUseSendRedirect = true;
			}
		} else {
			doGet(request, response);
		}

		if (isUseSendRedirect == true) {
			SessionUtil.getInstance().putValue(request, "User", model);
			if(model.getRole().equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/admin-home");
			} else {
				response.sendRedirect(request.getContextPath() + "/trangchu");
			}
		} else {
			String url = request.getRequestURI();

			if (url.startsWith(request.getContextPath() + "/dangnhap")) {
				url = "view/login.jsp";
			} else if (url.startsWith(request.getContextPath() + "/dangky")) {
				url = "view/register.jsp";
			}

			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}

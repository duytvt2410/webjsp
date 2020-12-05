package controller.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IBillDetailServicce;
import service.IUserService;
import service.imp.BillDetailService;
import service.imp.UserService;

@WebServlet(urlPatterns = { "/admin-user-list", "/admin-user-update-status", "/admin-user-delete"})
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IUserService userService = UserService.getInstance();
	private IBillDetailServicce billDetailService = BillDetailService.getInstance();


	private String url = null;

	private boolean isUseSendRedirect, isDelete;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/admin-user-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/bill-view")) {
			getPageView(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-user-delete") && request.getParameter("option") != null) {
			Map<String, String> map = deleteProcess(request);
			Set<String> set = map.keySet();
			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}
			request.setAttribute("role", "user");
			request.setAttribute("status", "active");
			request.setAttribute("listUser", userService.findAllByRoleAndStatus("user", "active"));
		} else if (url.startsWith(request.getContextPath() + "/admin-user-delete")) {
			isUseSendRedirect = true;
		} else if(url.startsWith(request.getContextPath() + "/admin-user-update-status")) {
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			if(id != null && status != null) {
				Map<String, String> map = userService.updateStatus(status, id);
				Set<String> set = map.keySet();
				for (String key : set) {
					request.setAttribute("alert", key);
					request.setAttribute("message", map.get(key));
				}
				request.setAttribute("role", "user");
				request.setAttribute("status", "active");
				request.setAttribute("listUser", userService.findAllByRoleAndStatus("user", "active"));
				url = "view/admin/user/list.jsp";
			} else {
				isUseSendRedirect = true;
			}
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-user-list");
		} else if (isDelete == false) {
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	}


	private void getPageList(HttpServletRequest request) {
		String role = request.getParameter("role");
		String status = request.getParameter("status");
		if (role != null && status != null) {
			request.setAttribute("role", role);
			request.setAttribute("status", status);

			request.setAttribute("listUser", userService.findAllByRoleAndStatus(role, status));
		} else {
			request.setAttribute("role", "user");
			request.setAttribute("status", "active");
			request.setAttribute("listUser", userService.findAllByRoleAndStatus("user", "active"));
		}
		url = "view/admin/user/list.jsp";
	}


	private void getPageView(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("listBill", billDetailService.findAllByBillId(request.getParameter("id")));
		} else {
			isUseSendRedirect = true;

		}
		url = "view/admin/bill/bill_detail.jsp";
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return userService.deleteOne(id[0]);
		}
		else {
			return userService.deleteAll(id);
		}
	}


}

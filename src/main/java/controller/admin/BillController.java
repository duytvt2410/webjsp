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
import service.IBillServicce;
import service.imp.BillDetailService;
import service.imp.BillService;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/admin-bill-list", "/bill-view", "/admin-bill-update-status", "/admin-bill-delete"})
public class BillController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IBillServicce billService = BillService.getInstance();
	private IBillDetailServicce billDetailService = BillDetailService.getInstance();
	

	private String url = null;

	private boolean isUseSendRedirect, isDelete;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();
		
		if (url.startsWith(request.getContextPath() + "/admin-bill-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/bill-view")) {
			getPageView(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-bill-delete") && request.getParameter("option") != null) {
			Map<String, String> map = deleteProcess(request);
			Set<String> set = map.keySet();
			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}
			request.setAttribute("listBill", billService.findAllByStatus("confirm"));
			request.setAttribute("status", "confirm");
		} else if (url.startsWith(request.getContextPath() + "/admin-bill-delete")) {
			isUseSendRedirect = true;
		} else if(url.startsWith(request.getContextPath() + "/admin-bill-update-status")) {
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			if(id != null && status != null) {
				Map<String, String> map = billService.updateStatus(status, id);
				Set<String> set = map.keySet();
				for (String key : set) {
					request.setAttribute("alert", key);
					request.setAttribute("message", map.get(key));
				}
				request.setAttribute("listBill", billService.findAllByStatus(status));
				request.setAttribute("status", status);
				url = SystemContain.URL_PAGE_BILL_LIST;
			} else {
				isUseSendRedirect = true;
			}
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-bill-list");
		} else if(isDelete == false){
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	}

	
	private void getPageList(HttpServletRequest request) {
		String status = request.getParameter("status");
		
		if(status != null) {
			request.setAttribute("listBill", billService.findAllByStatus(status));
			request.setAttribute("status", status);
		} else {
			request.setAttribute("listBill", billService.findAllByStatus("confirm"));
			request.setAttribute("status", "confirm");
		}
		url = SystemContain.URL_PAGE_BILL_LIST;
	}


	private void getPageView(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("billModel", billService.findOneById(request.getParameter("id")));
			request.setAttribute("listBill", billDetailService.findAllByBillId(request.getParameter("id")));
		} else {
			isUseSendRedirect = true;

		}
		url = SystemContain.URL_PAGE_BILL_DETAIL;
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return billService.delete(id[0]);
		}
		else {
			return billService.deleteAll(id);
		}
	}


}

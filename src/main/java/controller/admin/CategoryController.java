package controller.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryModel;
import service.ICategoryService;
import service.imp.CategoryService;
import utils.Utils;

@WebServlet(urlPatterns = { "/admin-category-add", "/admin-category-list", "/admin-category-edit",
		"/admin-category-delete", "/admin-category-view" })
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ICategoryService categoryService = CategoryService.getInstance();

	private String option = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		boolean isUseSendRedirect = false;

		String type = Utils.getUrlPatterns(request.getServletPath());

		String url = getURLPage(request.getServletPath());

		if (type.equalsIgnoreCase("admin-category-add")) {
		} else if (type.equalsIgnoreCase("admin-category-list")) {
			String isAccessories = request.getParameter("isAccessories");
			String status = request.getParameter("status");
			if (isAccessories != null && status != null) {
				request.setAttribute("isAccessories", isAccessories);
				request.setAttribute("status", status);
				if (isAccessories.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
					request.setAttribute("listCategory", categoryService.findAll());
				} else {
					request.setAttribute("listCategory",
							categoryService.findAllByIsAccessoriesAndStatus(isAccessories, status));
				}
			} else {
				request.setAttribute("isAccessories", "all");
				request.setAttribute("status", "active");
				request.setAttribute("listCategory", categoryService.findAllByIsAccessoriesAndStatus("all", "active"));
			}
		} else if (type.equalsIgnoreCase("admin-category-edit")) {
			if (request.getParameter("id") != null) {
				try {
					request.setAttribute("categoryModel",
							categoryService.findOneById(Long.parseLong(request.getParameter("id"))));
				} catch (Exception e) {
					isUseSendRedirect = true;
				}

			} else {
				isUseSendRedirect = true;

			}
		} else if (type.equalsIgnoreCase("admin-category-view")) {
			if (request.getParameter("id") != null) {
				try {
					request.setAttribute("categoryModel",
							categoryService.findOneById(Long.parseLong(request.getParameter("id"))));
				} catch (Exception e) {
					isUseSendRedirect = true;
				}

			} else {
				isUseSendRedirect = true;

			}
		} else if (type.equalsIgnoreCase("admin-category-delete")) {
			if (request.getParameter("id") != null) {
				Map<String, String> map = null;
				try {
					map = categoryService.delete(Long.parseLong(request.getParameter("id")));
				
				} catch (Exception e) {
					isUseSendRedirect = true;
				}

				Set<String> set = map.keySet();

				for (String key : set) {
					request.setAttribute("alert", key);
					request.setAttribute("message", map.get(key));
				}
			} else {
				isUseSendRedirect = true;
			}
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-category-list");
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		option = request.getParameter("option");

		if (option != null) {

			CategoryModel model = new CategoryModel();

			try {
				if (request.getParameter("id") != null)
					model.setId(Long.parseLong(request.getParameter("id")));
			} catch (Exception e) {
				model.setId(0L);
			}

			model.setName(request.getParameter("category_name"));
			model.setStatus(request.getParameter("status"));
			model.setIsAccessories(request.getParameter("isAccessories"));

			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				map = categoryService.insert(model);
			} else if (option.equalsIgnoreCase("edit")) {
				map = categoryService.update(model, model.getId());
			} else {
				map = categoryService.delete(model.getId());
			}

			Set<String> set = map.keySet();

			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}

			request.setAttribute("categoryModel", model);
			request.getRequestDispatcher(getURLPage(request.getServletPath())).forward(request, response);
		} else {
			doGet(request, response);
		}
	}

	private String getURLPage(String type) {
		String url = null;
		type = Utils.getUrlPatterns(type);
		if (type.equalsIgnoreCase("admin-category-add")) {
			url = "view/admin/category/add.jsp";
		} else if (type.equalsIgnoreCase("admin-category-list")) {
			url = "view/admin/category/list.jsp";
		} else if (type.equalsIgnoreCase("admin-category-edit")) {
			url = "view/admin/category/edit.jsp";
		} else if (type.equalsIgnoreCase("admin-category-view")) {
			url = "view/admin/category/view.jsp";
		} else if (type.equalsIgnoreCase("admin-category-delete")) {
			url = "view/admin/category/empty.jsp";
		}
		return url;
	}
}

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
import model.UserModel;
import service.ICategoryService;
import service.imp.CategoryService;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/admin-category-add", "/admin-category-list", "/admin-category-edit",
		"/admin-category-delete", "/admin-category-view" })
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ICategoryService categoryService = CategoryService.getInstance();

	private String option = null;

	private String url = null;

	private boolean isUseSendRedirect, isDelete;

	private CategoryModel model;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/admin-category-add")) {
			getPageAdd(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-category-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-category-edit")) {
			getPageEdit(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-category-view")) {
			getPageView(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-category-delete") && request.getParameter("option") != null) {
			doPost(request, response);
			isDelete = true;
		} else if (url.startsWith(request.getContextPath() + "/admin-category-delete")) {
			isUseSendRedirect = true;
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-category-list");
		} else if(isDelete == false){
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		option = request.getParameter("option");
		isUseSendRedirect = false;
		if (option != null) {
			model = getModelFromForm(request);
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
			
			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				model.setCreateBy(user.getId());
				map = addProcess(request);
			} else if (option.equalsIgnoreCase("edit")) {
				model.setUpdateBy(user.getId());
				map = editProcess(request);
			} else if (option.equalsIgnoreCase("delete")) {
				map = deleteProcess(request);
			}
			Set<String> set = map.keySet();
			String alert = "";
			String message = "";
			for (String key : set) {
				alert = key;
				message = map.get(key);
			}

			request.setAttribute("alert", alert);
			request.setAttribute("message", message);
			request.setAttribute("categoryModel", model);
			request.getRequestDispatcher(url).forward(request, response);

		} else {
			doGet(request, response);
		}
	}

	private CategoryModel getModelFromForm(HttpServletRequest request) {
		CategoryModel model = new CategoryModel();
		if (request.getParameter("id") != null)
			model.setId(request.getParameter("id"));
		model.setName(request.getParameter("category_name"));
		model.setStatus(request.getParameter("status"));
		model.setIsAccessories(request.getParameter("isAccessories"));
		return model;
	}

	private void getPageAdd(HttpServletRequest request) {
		this.url = SystemContain.URL_PAGE_CATEGORY_ADD;
	}

	private void getPageList(HttpServletRequest request) {
		String isAccessories = request.getParameter("isAccessories");
		String status = request.getParameter("status");
		if (isAccessories != null && status != null) {
			request.setAttribute("isAccessories", isAccessories);
			request.setAttribute("status", status);
				request.setAttribute("listCategory",
						categoryService.findAllByIsAccessoriesAndStatus(isAccessories, status));
			
		} else {
			request.setAttribute("isAccessories", "yes");
			request.setAttribute("status", "active");
			request.setAttribute("listCategory", categoryService.findAllByIsAccessoriesAndStatus("yes", "active"));
		}
		url = SystemContain.URL_PAGE_CATEGORY_LIST;
	}

	private void getPageEdit(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("categoryModel", categoryService.findOneById(request.getParameter("id")));
		} else {
			isUseSendRedirect = true;
		}
		url = SystemContain.URL_PAGE_CATEGORY_EDIT;
	}

	private void getPageView(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("categoryModel", categoryService.findOneById(request.getParameter("id")));
		} else {
			isUseSendRedirect = true;

		}
		url = SystemContain.URL_PAGE_CATEGORY_VIEW;
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return categoryService.delete(id[0]);
		}
		else {
			return categoryService.deleteAll(id);
		}
	}

	private Map<String, String> addProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_CATEGORY_ADD;
		return categoryService.insert(model);
	}

	private Map<String, String> editProcess(HttpServletRequest request) {

		url = SystemContain.URL_PAGE_CATEGORY_EDIT;
		return categoryService.update(model, model.getId());
	}

}

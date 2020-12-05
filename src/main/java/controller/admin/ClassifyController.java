package controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryModel;
import model.ClassifyModel;
import model.UserModel;
import service.ICategoryService;
import service.IClassifyService;
import service.imp.CategoryService;
import service.imp.ClassifyService;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/admin-classify-add", "/admin-classify-list","/admin-classify-delete", "/admin-classify-edit",
		"/admin-classify-view" })
public class ClassifyController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private String url = null;

	private boolean isUseSendRedirect, isDelete;

	private ClassifyModel model;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/admin-classify-add")) {
			getPageAdd(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-classify-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-classify-edit")) {
			getPageEdit(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-classify-view")) {
			getPageView(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-classify-delete") && request.getParameter("option") != null) {
			doPost(request, response);
			isDelete = true;
		}else if (url.startsWith(request.getContextPath() + "/admin-classify-delete")) {
			isUseSendRedirect = true;
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-classify-list");
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
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("classifyModel", model);
			request.getRequestDispatcher(url).forward(request, response);

		} else {
			doGet(request, response);
		}
	}
	
	private ClassifyModel getModelFromForm(HttpServletRequest request) {
		ClassifyModel model = new ClassifyModel();
		if(request.getParameter("id") != null) model.setId(request.getParameter("id"));
		model.setName(request.getParameter("classify_name"));
		model.setStatus(request.getParameter("status"));
		model.setCategoryAlias(request.getParameter("category"));
		return model;
	}

	private void getPageAdd(HttpServletRequest request) {
		request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
		url = SystemContain.URL_PAGE_CLASSIFY_ADD;
	}

	private void getPageList(HttpServletRequest request) {
		List<CategoryModel> listCategory = categoryService.findAllByStatus("active");
		String categoryAlias = request.getParameter("category");
		String status = request.getParameter("status");
		if (categoryAlias != null && status != null) {
			request.setAttribute("category", categoryAlias);
			request.setAttribute("status", status);
			request.setAttribute("listClassify", classifyService.findAllByCategoryAliasAndStatus(categoryAlias, status));
		} else {
			if (listCategory.size() != 0) {
				request.setAttribute("category", listCategory.get(0).getAlias());
				request.setAttribute("status", "active");
				request.setAttribute("listClassify", classifyService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active"));
			}
		}
		request.setAttribute("listCategory", listCategory);
		url = SystemContain.URL_PAGE_CLASSIFY_LIST;
	}

	private void getPageEdit(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("classifyModel", classifyService.findOneById(request.getParameter("id")));
			url = SystemContain.URL_PAGE_CLASSIFY_EDIT;
		} else {
			isUseSendRedirect = true;
		}
	}

	private void getPageView(HttpServletRequest request) {
		request.setAttribute("classifyModel", classifyService.findOneById(request.getParameter("id")));
		url = SystemContain.URL_PAGE_CLASSIFY_VIEW;
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return classifyService.delete(id[0]);
		}
		else {
			return classifyService.deleteAll(id);
		}
	}

	private Map<String, String> addProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_CLASSIFY_ADD;
		return classifyService.insert(model);
	}

	private Map<String, String> editProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_CLASSIFY_EDIT;
		return classifyService.update(model, model.getId());
	}
}

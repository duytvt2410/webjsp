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

import model.BrandModel;
import model.CategoryModel;
import model.UserModel;
import service.IBrandService;
import service.ICategoryService;
import service.imp.BrandService;
import service.imp.CategoryService;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/admin-brand-add", "/admin-brand-list", "/admin-brand-edit","/admin-brand-delete", "/admin-brand-view" })
public class BrandController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private String url = null;

	private boolean isUseSendRedirect, isDelete;

	private BrandModel model;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IBrandService brandService = BrandService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/admin-brand-add")) {
			getPageAdd(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-brand-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-brand-edit")) {
			getPageEdit(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-brand-view")) {
			getPageView(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-brand-delete") && request.getParameter("option") != null) {
			doPost(request, response);
			isDelete = true;
		} else if(url.startsWith(request.getContextPath() + "/admin-brand-delete")) {
			isUseSendRedirect = true;
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-brand-list");
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
				request.setAttribute("brandModel", model);
				request.getRequestDispatcher(url).forward(request, response);
			
		} else {
			doGet(request, response);
		}
		
	}
	
	private BrandModel getModelFromForm(HttpServletRequest request) {
		BrandModel model = new BrandModel();
		if(request.getParameter("id") != null) model.setId(request.getParameter("id"));
		model.setName(request.getParameter("brand_name"));
		model.setStatus(request.getParameter("status"));
		model.setCategoryAlias(request.getParameter("category"));
		return model;
	}

	private void getPageAdd(HttpServletRequest request) {
		request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
		url = SystemContain.URL_PAGE_BRAND_ADD;
	}

	private void getPageList(HttpServletRequest request) {
		List<CategoryModel> listCategory = categoryService.findAllByStatus("active");
		String categoryAlias = request.getParameter("category");
		String status = request.getParameter("status");
		if (categoryAlias != null && status != null) {
			request.setAttribute("category", categoryAlias);
			request.setAttribute("status", status);

				request.setAttribute("listBrand", brandService.findAllByCategoryAliasAndStatus(categoryAlias, status));
		} else {
			if (listCategory.size() != 0) {
				request.setAttribute("category", listCategory.get(0).getAlias());
				request.setAttribute("status", "active");
				request.setAttribute("listBrand", brandService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active"));
			} 
		}
		request.setAttribute("listCategory", listCategory);
		url = SystemContain.URL_PAGE_BRAND_LIST;
	}

	private void getPageEdit(HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("brandModel", brandService.findOneById(request.getParameter("id")));
			url = SystemContain.URL_PAGE_BRAND_EDIT;
		} else {
			isUseSendRedirect = true;
		}
	}

	private void getPageView(HttpServletRequest request) {
		request.setAttribute("brandModel", brandService.findOneById(request.getParameter("id")));
		url = SystemContain.URL_PAGE_BRAND_VIEW;
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return brandService.delete(id[0]);
		}
		else if(id!= null && id.length > 1){
			return brandService.deleteAll(id);
		} else {
			
			return null;
		}
	}

	private Map<String, String> addProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_BRAND_ADD;
		return brandService.insert(model);
	}

	private Map<String, String> editProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_BRAND_EDIT;
		return brandService.update(model, model.getId());
	}
}

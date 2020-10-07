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
import service.IBrandService;
import service.ICategoryService;
import service.imp.BrandService;
import service.imp.CategoryService;
import utils.Utils;

@WebServlet(urlPatterns = { "/admin-brand-add", "/admin-brand-list", "/admin-brand-edit", "/admin-brand-view" })
public class BrandController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IBrandService brandService = BrandService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = null;

		boolean isUseSendRedirect = false;

		String type = Utils.getUrlPatterns(request.getServletPath());
		if (type.equalsIgnoreCase("admin-brand-add")) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			url = "view/admin/brand/add.jsp";
		} else if (type.equalsIgnoreCase("admin-brand-list")) {
			List<CategoryModel> listCategory = categoryService.findAllByStatus("active");
			String categoryAlias = request.getParameter("categoryAlias");
			String status = request.getParameter("status");
			if (categoryAlias != null && status != null) {
				request.setAttribute("categoryAlias", categoryAlias);
				request.setAttribute("status", status);

				if (categoryAlias.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
					request.setAttribute("listBrand", brandService.findAll());
				} else {
					request.setAttribute("listBrand",
							brandService.findAllByCategoryAliasAndStatus(categoryAlias, status));
				}
			} else {
				if (listCategory.size() != 0) {
					request.setAttribute("categoryAlias", listCategory.get(0).getAlias());
					request.setAttribute("status", "active");
					request.setAttribute("listBrand",
							brandService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active"));
				} else {
					request.setAttribute("categoryAlias", "all");
					request.setAttribute("status", "active");
					request.setAttribute("listBrand", brandService.findAllByCategoryAliasAndStatus("all", "active"));
				}
			}
			request.setAttribute("listCategory", listCategory);
			url = "view/admin/brand/list.jsp";
		} else if (type.equalsIgnoreCase("admin-brand-edit")) {
			if (request.getParameter("id") != null) {
				request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
				try {
					request.setAttribute("brandModel", brandService.findOneById(Long.parseLong(request.getParameter("id"))));
				} catch (Exception e) {
					isUseSendRedirect = true;
				}
				url = "view/admin/brand/edit.jsp";
			} else {
				isUseSendRedirect = true;

			}
		} else if (type.equalsIgnoreCase("admin-brand-view")) {
			try {
				request.setAttribute("brandModel", brandService.findOneById(Long.parseLong(request.getParameter("id"))));
			} catch (Exception e) {
				isUseSendRedirect = true;
			}
			url = "view/admin/brand/view.jsp";
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-brand-list");
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		option = request.getParameter("option");
		if (option != null) {
			
			BrandModel model = new BrandModel();
			try {
				if(request.getParameter("id") != null) model.setId(Long.parseLong(request.getParameter("id")));
			} catch (Exception e) {
				model.setId(0L);
			}
			model.setName(request.getParameter("brand_name"));
			model.setStatus(request.getParameter("status"));
			model.setCategoryAlias(request.getParameter("category"));

			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				map = brandService.insert(model);
			} else if (option.equalsIgnoreCase("edit")) {
				map = brandService.update(model, model.getId());
				request.setAttribute("id", model.getId());
			}

			Set<String> set = map.keySet();

			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}

			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("brandModel", model);

			request.getRequestDispatcher(getPageCategory(request.getServletPath())).forward(request, response);
		} else {
			doGet(request, response);
		}
	}

	private String getPageCategory(String type) {
		String url = null;
		type = Utils.getUrlPatterns(type);
		if (type.equalsIgnoreCase("admin-brand-add")) {
			url = "view/admin/brand/add.jsp";
		} else if (type.equalsIgnoreCase("admin-brand-list")) {
			url = "view/admin/brand/list.jsp";
		} else if (type.equalsIgnoreCase("admin-brand-edit")) {
			url = "view/admin/brand/edit.jsp";
		}
		return url;
	}
}

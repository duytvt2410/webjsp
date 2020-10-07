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
import service.ICategoryService;
import service.IClassifyService;
import service.imp.CategoryService;
import service.imp.ClassifyService;
import utils.Utils;

@WebServlet(urlPatterns = { "/admin-classify-add", "/admin-classify-list", "/admin-classify-edit",
		"/admin-classify-view" })
public class ClassifyController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = null;

		boolean isUseSendRedirect = false;

		String type = Utils.getUrlPatterns(request.getServletPath());
		if (type.equalsIgnoreCase("admin-classify-add")) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			url = "view/admin/classify/add.jsp";
		} else if (type.equalsIgnoreCase("admin-classify-list")) {
			List<CategoryModel> listCategory = categoryService.findAllByStatus("active");
			String categoryAlias = request.getParameter("categoryAlias");
			String status = request.getParameter("status");
			if (categoryAlias != null && status != null) {
				request.setAttribute("categoryAlias", categoryAlias);
				request.setAttribute("status", status);

				if (categoryAlias.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
					request.setAttribute("listClassify", classifyService.findAll());
				} else {
					request.setAttribute("listClassify",
							classifyService.findAllByCategoryAliasAndStatus(categoryAlias, status));
				}
			} else {
				if(listCategory.size() != 0) {
				request.setAttribute("categoryAlias", listCategory.get(0).getAlias());
				request.setAttribute("status", "active");
				request.setAttribute("listClassify",
						classifyService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active"));
				} else {
					request.setAttribute("categoryAlias", "all");
					request.setAttribute("status", "active");
					request.setAttribute("listClassify",
							classifyService.findAllByCategoryAliasAndStatus("all", "active"));
				}
			}
			request.setAttribute("listCategory", listCategory);
			url = "view/admin/classify/list.jsp";
		} else if (type.equalsIgnoreCase("admin-classify-edit")) {
			if (request.getParameter("id") != null) {
				request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
				
				try {
					request.setAttribute("classifyModel", classifyService.findOneById(Long.parseLong(request.getParameter("id"))));
				} catch (Exception e) {
					isUseSendRedirect = true;
				}
				
				url = "view/admin/classify/edit.jsp";
			} else {
				isUseSendRedirect = true;

			}
		} else if (type.equalsIgnoreCase("admin-classify-view")) {
			try {
				request.setAttribute("classifyModel", classifyService.findOneById(Long.parseLong(request.getParameter("id"))));
			} catch (Exception e) {
				isUseSendRedirect = true;
			}
			url = "view/admin/classify/view.jsp";
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-classify-list");
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		option = request.getParameter("option");
		if (option != null) {
			ClassifyModel model = new ClassifyModel();
			try {
				if(request.getParameter("id") != null) model.setId(Long.parseLong(request.getParameter("id")));
			} catch (Exception e) {
				model.setId(0L);
			}
			model.setName(request.getParameter("classify_name"));
			model.setStatus(request.getParameter("status"));
			model.setCategoryAlias(request.getParameter("category"));

			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				map = classifyService.insert(model);
			} else if (option.equalsIgnoreCase("edit")) {
				map = classifyService.update(model, model.getId());
				request.setAttribute("id", model.getId());
			}

			Set<String> set = map.keySet();

			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}

			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("classifyModel", model);

			request.getRequestDispatcher(getPageCategory(request.getServletPath())).forward(request, response);
		} else {
			doGet(request, response);
		}
	}

	private String getPageCategory(String type) {
		String url = null;
		type = Utils.getUrlPatterns(type);
		if (type.equalsIgnoreCase("admin-classify-add")) {
			url = "view/admin/classify/add.jsp";
		} else if (type.equalsIgnoreCase("admin-classify-list")) {
			url = "view/admin/classify/list.jsp";
		} else if (type.equalsIgnoreCase("admin-classify-edit")) {
			url = "view/admin/classify/edit.jsp";
		}
		return url;
	}
}

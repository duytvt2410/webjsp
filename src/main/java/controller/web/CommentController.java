package controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentModel;
import model.ProductModel;
import model.UserModel;
import service.ICommentService;
import service.IProductService;
import service.imp.CommentService;
import service.imp.ProductService;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/binhluan", "/suabinhluan", "/xoabinhluan"})
public class CommentController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ICommentService commentService = CommentService.getInstance();
	private IProductService productService = ProductService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		
		String url = request.getRequestURI();
		CommentModel model = new CommentModel();
		model.setId(request.getParameter("id"));
		
		model.setUserId(request.getParameter("user_id"));
		model.setProductId(request.getParameter("product_id"));
		model.setContent(request.getParameter("content"));
		
		int first = 1;
		int page = 1;
		int limit = 3;
		int length = 0;
		
		try {
			model.setStar(Integer.parseInt(request.getParameter("star")));
		} catch (Exception e) {
			model.setStar(1);
		}
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
		

		if (url.startsWith(request.getContextPath() + "/binhluan")) {
			Map<String, String> map = new HashMap<String, String>();
			if(option != null && option.equals("add")) {
				model.setCreateBy(user.getId());
				map = commentService.insert(model);
			} else if(option != null && option.equals("edit")) {
				model.setUpdateBy(user.getId());
				map = commentService.update(model);
			} else if (option != null && option.equals("delete")) {
				commentService.delete(model.getId());
			}
			Set<String> set = map.keySet();
			String message = "";
			for (String key : set) {
				if(key.equals("danger")) {
				message = map.get(key);
				request.setAttribute("message", message);
				}
			}
			ProductModel p = productService.findOneById(model.getProductId());
			List<CommentModel> listComment = commentService.findAllAndLimit(p.getId(), limit, first - 1);
			length = commentService.countAllByProductId(p.getId());
			request.setAttribute("listComment", listComment);
			request.setAttribute("first", first);
			request.setAttribute("page", page);
			request.setAttribute("limit", limit);
			request.setAttribute("length", length);
			request.setAttribute("productModel", p);
			request.setAttribute("comment", model);
			
			url = SystemContain.URL_PAGE_REVIEW;
			request.getRequestDispatcher(url).forward(request, response);
		
		} else if (url.startsWith(request.getContextPath() + "/suabinhluan")) {
			String id =request.getParameter("id");
			if(id != null && id != "") {
				request.setAttribute("comment", commentService.findOneById(id));
		
				url = SystemContain.URL_PAGE_EDIT_REVIEW;
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}
}

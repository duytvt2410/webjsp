package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentModel;
import model.ProductModel;
import service.ICommentService;
import service.IProductService;
import service.imp.CommentService;
import service.imp.ProductService;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/chitietsp"})
public class DetailProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IProductService productService = ProductService.getInstance();
	private ICommentService commentService = CommentService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String alias = request.getQueryString().split("=")[1];
		alias = alias.split("&")[0];
		
		if(alias != null) {
			String phantrang = request.getParameter("phantrang");
			int first = 1;
			int page = 1;
			int limit = 3;
			int length = 0;
			if (phantrang != null) {
				first = Integer.parseInt(request.getParameter("first"));
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			ProductModel product = productService.findOneByAlias(alias);
			List<ProductModel> listProduct =  null;
			List<CommentModel> listComment =  null;
			if(product != null) {
				listProduct = productService.findAllRelatedProduct(product.getBrandId(), product.getId(), "active");
				listComment = commentService.findAllAndLimit(product.getId(), limit, first - 1);
				length = commentService.countAllByProductId(product.getId());
				request.setAttribute("productModel", product);
				request.setAttribute("listRelatedProduct", listProduct);
				request.setAttribute("listComment", listComment);
				request.setAttribute("first", first);
				request.setAttribute("page", page);
				request.setAttribute("limit", limit);
				request.setAttribute("length", length);
				
				
				
				if (phantrang != null) {
					request.getRequestDispatcher(SystemContain.URL_PAGE_REVIEW).forward(request, response);
				} else {
					request.getRequestDispatcher(SystemContain.URL_PAGE_PRODUCT).forward(request, response);

				}
			} else {
				request.getRequestDispatcher("error-404.jsp").forward(request, response);
			}
			
			
		} else {
			response.sendRedirect(request.getContextPath() + "/trangchu");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}

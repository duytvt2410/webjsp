package controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.ProductModel;
import service.IProductService;
import service.imp.ProductService;
import utils.SessionUtil;

@WebServlet("/giohang")
public class CartController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IProductService productService = new ProductService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String option = request.getParameter("option");
		String quantity = request.getParameter("quantity");

		Cart c = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		if (option != null && id != null) {
			if (option.equalsIgnoreCase("add")) {

				ProductModel p = productService.findOneById(Long.parseLong(id));
				if (c == null)
					c = new Cart();
				if (p != null)
					c.put(p);

			} else if (option.equalsIgnoreCase("update")) {
				if (quantity != null) {
					if (c == null)
						c = new Cart();
					c.put(Long.parseLong(id), Integer.parseInt(quantity));
				}
			} else if (option.equalsIgnoreCase("remove")) {
				if (c == null)
					c = new Cart();
				c.remove(Long.parseLong(id));
			}
		} 
		SessionUtil.getInstance().putValue(request, "Cart", c);
		
		response.sendRedirect(request.getContextPath() + "/trangchu");
	}
}

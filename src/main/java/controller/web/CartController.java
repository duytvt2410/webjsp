package controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.ProductModel;
import service.IProductService;
import service.imp.ProductService;
import utils.SessionUtil;
import utils.SystemContain;

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
		String id = request.getParameter("id");
		String option = request.getParameter("option");
		String qtyadd = request.getParameter("qty");
		String url = "";
		Cart c = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		if (option != null && id != null) {
			if (option.equalsIgnoreCase("add") || option.equalsIgnoreCase("update")) {
				ProductModel p = productService.findOneById(id);
				int qty = 0;
				try {
				 qty = Integer.parseInt(qtyadd);
				} catch (Exception e) {
				 qty = 0;
				}
				
				
				if(option.equalsIgnoreCase("add")) {
					if(qty != 0) {
						if (c == null)
							c = new Cart();
						if (p != null) {
							c.put(p, qty);
						}
					}
					url = SystemContain.URL_PAGE_MINICART;
				} else {
					if(qty != 0) {
						if (c == null)
							c = new Cart();
						if (p != null) {
							c.get(p.getId()).updateQtyInCart(-c.get(p.getId()).getQtyInCart());
							
							c.put(p, qty);
						}
					}
					url = SystemContain.URL_PAGE_CART;
				}
				
			} else if (option.equalsIgnoreCase("remove")) {
				if (c == null)
					c = new Cart();
				c.remove(id);
				url = SystemContain.URL_PAGE_MINICART;
			} else if(option.equalsIgnoreCase("delete")) {
				if (c == null)
					c = new Cart();
				c.remove(id);
				url = SystemContain.URL_PAGE_CART;
			}
			
		} else {
			url = SystemContain.URL_PAGE_CART;
		}
		SessionUtil.getInstance().putValue(request, "Cart", c);

		request.getRequestDispatcher(url).forward(request, response);
	}
}

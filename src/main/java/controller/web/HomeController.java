package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;
import service.IProductService;
import service.imp.ProductService;

@WebServlet(urlPatterns = { "/trangchu"})
public class  HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IProductService productService = ProductService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ProductModel> listProduct = new ArrayList<ProductModel>();

		// Lấy sản phẩm mới nhất

		
			// - Điện thoại
			listProduct = productService.findAllByCategoryAndIsNewAndStatusAndLimit("dien-thoai", "yes", "active", 10);

			request.setAttribute("listProductIsNewPhone", listProduct);

			// - Laptop
			listProduct = productService.findAllByCategoryAndIsNewAndStatusAndLimit("laptop", "yes", "active", 10);

			request.setAttribute("listProductIsNewLaptop", listProduct);

			// - Phụ kiện
			listProduct = productService.findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit("yes", "yes", "active", 10);

			request.setAttribute("listProductIsNewAccessories", listProduct);

			// Lấy sản phẩm đang giảm giá
			// - Điện thoại
			listProduct = productService.findAllByCategoryAndStatusAndPricePromotion("dien-thoai", "active");

			request.setAttribute("listProductIsPhoneDiscount", listProduct);

			// - Laptop
			listProduct = productService.findAllByCategoryAndStatusAndPricePromotion("laptop", "active");

			request.setAttribute("listProductIsLaptopDiscount", listProduct);

			// - Phụ kiện
			listProduct = productService.findAllByCategoryIsAccessoriesAndStatusAndPricePromotion("yes", "active");

			request.setAttribute("listProductIsAccessoriesDiscount", listProduct);

			request.getRequestDispatcher("view/web/home.jsp").forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}

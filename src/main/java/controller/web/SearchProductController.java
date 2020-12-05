package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;
import service.IProductService;
import service.imp.ProductService;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/timkiem"})
public class SearchProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IProductService productService = ProductService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key_word = request.getParameter("tukhoa");
		if(key_word == null) {
			key_word = "";
		}
		String keys [] = key_word.split(" ");
		
		String condition = "";
		for(int i = 0; i < keys.length; i++) {
			if(i == 0) {
				condition += " product.name LIKE ?";
			} else {
				condition += " AND  product.name LIKE ?";
			}
			keys[i] = "%" + keys[i] + "%";
		}
		if(key_word != "") {
       List<ProductModel> listProduct =  productService.findAllByKeyword(condition, keys);
       Collections.sort(listProduct, new Comparator<ProductModel>() {
			@Override
			public int compare(ProductModel c2, ProductModel c1) {

				return c1.getCategoryName().compareTo(c2.getCategoryName());
			}
	});
       request.setAttribute("listProduct", listProduct);
		}
       request.setAttribute("tukhoa", key_word);
       request.getRequestDispatcher(SystemContain.URL_PAGE_RESULTS_SEARCH_PRODUCT).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
}



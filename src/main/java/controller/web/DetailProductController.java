package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ImagesModel;
import model.ProductModel;
import service.IProductService;
import service.InterfaceImagesService;
import service.imp.ImagesService;
import service.imp.ProductService;

@WebServlet(urlPatterns = { "/chitietsp"})
public class DetailProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IProductService productService = ProductService.getInstance();

	private InterfaceImagesService imagesService = ImagesService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String alias = request.getParameter("tensp");
		
		if(alias != null) {
			
			ProductModel product = productService.findOneByAlias(alias);
			List<ProductModel> listProduct =  null;
			if(product != null) {
				listProduct = productService.findAllRelatedProduct(product.getBrandId(), product.getId(), "active");
				List<ImagesModel> listImage = imagesService.findAllByProductId(product.getId());
				product.setListImage(listImage);
				
				for(ProductModel p : listProduct) {
					p.setListImage(imagesService.findAllByProductId(p.getId()));
				}
			}
			
			request.setAttribute("productModel", product);
			request.setAttribute("listRelatedProduct", listProduct);
			request.getRequestDispatcher("view/web/product.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/trangchu");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}

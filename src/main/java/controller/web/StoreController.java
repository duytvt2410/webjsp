package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BrandModel;
import model.CategoryModel;
import model.ClassifyModel;
import model.ProductModel;
import service.IBrandService;
import service.ICategoryService;
import service.IClassifyService;
import service.IProductService;
import service.imp.BrandService;
import service.imp.CategoryService;
import service.imp.ClassifyService;
import service.imp.ProductService;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/dienthoai", "/laptop","/phukien", "/phantrang" })
public class StoreController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IProductService productService = ProductService.getInstance();

	private ICategoryService categoryService = CategoryService.getInstance();

	private IBrandService brandService = BrandService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filterBrand = request.getParameter("nsx");
		String filterClassify[] = request.getParameterValues("nhomsp");

		boolean isUseSendRedirect = false;
		String url = request.getRequestURI();

		List<BrandModel> listBrand = null;
		List<ClassifyModel> listClassify = null;
		List<CategoryModel> listCategory = null;
		List<ProductModel> listProduct = null;
		String paramer = "";
		String sqlConditon = "";

		if (filterBrand != null && !filterBrand.equals("")) {
			sqlConditon += " AND brand.id = ? ";
			paramer += filterBrand + "dt14082410dt";
		}

		if (filterClassify != null) {
			sqlConditon += " AND product_classify.classify_id IN (";
			for (int i = 0; i < filterClassify.length; i++) {
				if (!filterClassify[i].equals("")) {
					if (i == 0) {
						sqlConditon += "?";
					} else {
						sqlConditon += ",?";
					}

					paramer += filterClassify[i] + "dt14082410dt";
				}
			}
			sqlConditon += " ) GROUP  BY product.id HAVING Count(DISTINCT product_classify.classify_id) = "
					+ filterClassify.length;
		}
		String phantrang = request.getParameter("phantrang");
		int first = 1;
		int page = 1;
		int limit = 6;
		int length = 0;
		if (phantrang != null) {
			first = Integer.parseInt(request.getParameter("first"));
			page = Integer.parseInt(request.getParameter("page"));
		}

		if (url.startsWith(request.getContextPath() + "/phukien")) {
			listCategory = categoryService.findAllByIsAccessoriesAndStatus("yes", "active");
			String theloai = request.getParameter("theloai");
			if (theloai != null && !theloai.equals("")) {
				listBrand = brandService.findAllByCategoryAliasAndStatus(theloai, "active");
				listClassify = classifyService.findAllByCategoryAliasAndStatus(theloai, "active");
				request.setAttribute("theloai", theloai);
				String[] arrPararameter = paramer.split("dt14082410dt");
				Long ids[] = new Long[arrPararameter.length];
				try {
					for (int i = 0; i < arrPararameter.length; i++) {
						ids[i] = Long.parseLong(arrPararameter[i]);

					}
					listProduct = productService.findAllCustomCondition(sqlConditon, theloai, "active", limit, first - 1,
							ids);
					length = productService.countAllCustomCondition(sqlConditon, theloai, "active", ids);
				} catch (Exception e) {
					listProduct = productService.findAllCustomCondition("", theloai, "active", limit, first - 1,
							arrPararameter);
					length = productService.countAllCustomCondition("", theloai, "active", arrPararameter);
				}
			} else {
				if (listCategory.size() != 0) {
					listBrand = brandService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active");
					listClassify = classifyService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(),
							"active");
					request.setAttribute("theloai", listCategory.get(0).getAlias());
					String[] arrPararameter = paramer.split("dt14082410dt");
					Long ids[] = new Long[arrPararameter.length];
					try {
						for (int i = 0; i < arrPararameter.length; i++) {
							ids[i] = Long.parseLong(arrPararameter[i]);

						}
						listProduct = productService.findAllCustomCondition(sqlConditon, listCategory.get(0).getAlias(),
								"active", limit, first - 1, ids);
						length = productService.countAllCustomCondition(sqlConditon, listCategory.get(0).getAlias(),
								"active", ids);
					} catch (Exception e) {
						listProduct = productService.findAllCustomCondition("", listCategory.get(0).getAlias(),
								"active", limit, first - 1, arrPararameter);
						length = productService.countAllCustomCondition("", listCategory.get(0).getAlias(), "active",
								arrPararameter);
					}
				}
			}
		} else if (url.startsWith(request.getContextPath() + "/dienthoai")) {
			listBrand = brandService.findAllByCategoryAliasAndStatus("dien-thoai", "active");
			if (listBrand.size() != 0) {
				listClassify = classifyService.findAllByCategoryAliasAndStatus("dien-thoai", "active");

				String[] arrPararameter = paramer.split("dt14082410dt");
				Long ids[] = new Long[arrPararameter.length];
				try {
					for (int i = 0; i < arrPararameter.length; i++) {
						ids[i] = Long.parseLong(arrPararameter[i]);

					}
					listProduct = productService.findAllCustomCondition(sqlConditon, "dien-thoai", "active", limit, first - 1,
							ids);
					length = productService.countAllCustomCondition(sqlConditon, "dien-thoai", "active", ids);
				} catch (Exception e) {
					listProduct = productService.findAllCustomCondition("", "dien-thoai", "active", limit, first - 1,
							arrPararameter);
					length = productService.countAllCustomCondition("", "dien-thoai", "active", arrPararameter);
				}
			} else {
				isUseSendRedirect = true;
			}

		} else if(url.startsWith(request.getContextPath() + "/laptop")) {
			listBrand = brandService.findAllByCategoryAliasAndStatus("laptop", "active");
			if (listBrand.size() != 0) {
				listClassify = classifyService.findAllByCategoryAliasAndStatus("laptop", "active");

				String[] arrPararameter = paramer.split("dt14082410dt");
				Long ids[] = new Long[arrPararameter.length];
				try {
					for (int i = 0; i < arrPararameter.length; i++) {
						ids[i] = Long.parseLong(arrPararameter[i]);

					}
					listProduct = productService.findAllCustomCondition(sqlConditon, "laptop", "active", limit, first - 1,
							ids);
					length = productService.countAllCustomCondition(sqlConditon, "laptop", "active", ids);
				} catch (Exception e) {
					listProduct = productService.findAllCustomCondition("", "laptop", "active", limit, first - 1,
							arrPararameter);
					length = productService.countAllCustomCondition("", "laptop", "active", arrPararameter);
				}
			} else {
				isUseSendRedirect = true;
			}
		}

		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/trangchu");
		} else {
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("listBrand", listBrand);
			request.setAttribute("listClassify", listClassify);
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("first", first);
			request.setAttribute("page", page);
			request.setAttribute("limit", limit);
			request.setAttribute("length", length);

			if (phantrang != null) {
				request.getRequestDispatcher("view/web/getPageProduct.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher(SystemContain.URL_PAGE_STORE).forward(request, response);

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

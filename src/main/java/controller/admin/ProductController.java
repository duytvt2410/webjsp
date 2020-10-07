package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.ImagesModel;
import model.ProductClassifyModel;
import model.ProductModel;
import service.IBrandService;
import service.ICategoryService;
import service.IClassifyService;
import service.IProductClassifyService;
import service.IProductService;
import service.InterfaceImagesService;
import service.imp.BrandService;
import service.imp.CategoryService;
import service.imp.ClassifyService;
import service.imp.ImagesService;
import service.imp.ProductClassifyService;
import service.imp.ProductService;
import utils.Utils;

@WebServlet(urlPatterns = { "/admin-product-add", "/admin-product-list", "/admin-product-edit", "/admin-product-view",
		"/admin-product-getBrand", "/admin-product-getClassify" })
public class ProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IBrandService brandService = BrandService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	private IProductService productServie = ProductService.getInstance();
	
	private InterfaceImagesService imagesService = ImagesService.getInstance();
	
	private IProductClassifyService productClassiyServie = ProductClassifyService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isUseSendRedirect = false;

		String type = Utils.getUrlPatterns(request.getServletPath());

		String url = getURLPage(request.getServletPath());

		if (type.equalsIgnoreCase("admin-product-add")) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
		} else if (type.equalsIgnoreCase("admin-product-list")) {
			
			List<ProductModel> listProduct = productServie.findAll();

			for (int i = 0; i < listProduct.size(); i++) {
				List<ImagesModel> listImage = imagesService.findAllByProductId(listProduct.get(i).getId());

				listProduct.get(i).setListImage(listImage);
			}
			request.setAttribute("listProduct", listProduct);
		} else if (type.equalsIgnoreCase("admin-product-edit")) {
			String id = request.getParameter("id");
			if (id != null) {
				request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
				try {
					ProductModel productModel = productServie.findOneById(Long.parseLong(id));
					
					productModel.setListClassify(productClassiyServie.findAllByProductId(Long.parseLong(id)));
					List<ImagesModel> listImage = imagesService.findAllByProductId(Long.parseLong(id));
					productModel.setListImage(listImage);
					
					request.setAttribute("productModel", productModel);
					
				} catch (Exception e) {
					isUseSendRedirect = true;
				}
				
			} else {
				isUseSendRedirect = true;

			}
		} else if (type.equalsIgnoreCase("admin-brand-view")) {
			request.setAttribute("brandModel", brandService.findOneByAlias(request.getParameter("name")));
		} else if (type.equalsIgnoreCase("admin-product-getBrand")) {
			if (request.getParameter("category").equals("")) {
				request.setAttribute("empty", "yes");
			} else {
				request.setAttribute("empty", "no");
			}
			request.setAttribute("brand", request.getParameter("brand"));
			request.setAttribute("listBrand", brandService.findAllByCategoryAlias(request.getParameter("category")));
		} else if (type.equalsIgnoreCase("admin-product-getClassify")) {

			request.setAttribute("classify", request.getParameter("classify"));
			request.setAttribute("listClassify",
					classifyService.findAllByCategoryAlias(request.getParameter("category")));
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-product-list");
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ProductModel model = new ProductModel();

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			List<FileItem> itemImage = new ArrayList<FileItem>();

			List<ProductClassifyModel> listClassify = new ArrayList<ProductClassifyModel>();
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (fileItem.getFieldName().equals("option")) {
						option = fileItem.getString();
					} else if (fileItem.getFieldName().equals("name")) {

						model.setName(new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8"));
					} else if (fileItem.getFieldName().equals("id")) {
						try {
							model.setId(Long.parseLong(fileItem.getString()));
						} catch (Exception e) {
							model.setId(0L);
						}
					} else if (fileItem.getFieldName().equals("category")) {
						try {
							model.setCategoryAlias((fileItem.getString()));
						} catch (Exception e) {
							model.setId(0L);
						}
					} else if (fileItem.getFieldName().equals("brand")) {
						try {
							model.setBrandId(Long.parseLong(fileItem.getString()));
						} catch (Exception e) {
							model.setId(0L);
						}
					} else if (fileItem.getFieldName().equals("status")) {
						model.setStatus(fileItem.getString());
					} else if (fileItem.getFieldName().equals("classify")) {

						ProductClassifyModel classify = new ProductClassifyModel();
						try {
							classify.setClassifyId(Long.parseLong(fileItem.getString()));
						} catch (Exception e) {
							classify.setClassifyId(0L);
						}
						listClassify.add(classify);
					} else if (fileItem.getFieldName().equals("price")) {
						String getPriceFromForm = fileItem.getString();
						String priceSplitDot[] = getPriceFromForm.split("\\.");
						String price = "";
						for (String s : priceSplitDot) {
							price += s;
						}

						try {
							model.setPrice(Integer.parseInt(price));
						} catch (Exception e) {
							model.setPrice(0);
						}
					} else if (fileItem.getFieldName().equals("pricePromotional")) {
						String getPriceFromForm = fileItem.getString();
						String priceSplitDot[] = getPriceFromForm.split("\\.");
						String price = "";
						for (String s : priceSplitDot) {
							price += s;
						}
						try {
							model.setPricePromotional(Integer.parseInt(price));
						} catch (Exception e) {
							model.setPricePromotional(0);
						}
					} else if (fileItem.getFieldName().equals("qty")) {
						try {
							model.setQty(Integer.parseInt(fileItem.getString()));
						} catch (Exception e) {
							model.setQty(0);
						}
					} else if (fileItem.getFieldName().equals("promotionInformation")) {
						model.setPromotionInformation(new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8"));
					} else if (fileItem.getFieldName().equals("description")) {
						model.setDescription(new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8"));
					} else if (fileItem.getFieldName().equals("specifications")) {
						model.setSpecifications(new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8"));
					}

				} else {
					if (fileItem.getSize() > 0) {
						if (fileItem.getFieldName().equals("file-image")) {
							model.setFieldsImageProduct(fileItem);
						} else if(fileItem.getFieldName().equals("file-images")) {
							itemImage.add(fileItem);
						}
					}
				}
			}
			model.setListClassify(listClassify);
			model.setFieldsImagesDetail(itemImage);
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		if (option != null) {

			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				map = productServie.insert(model);
			} else if (option.equalsIgnoreCase("edit")) {
				map = productServie.update(model, model.getId());
				request.setAttribute("id", model.getId());
			}

			Set<String> set = map.keySet();

			for (String key : set) {
				request.setAttribute("alert", key);
				request.setAttribute("message", map.get(key));
			}
			List<ImagesModel> listImage = imagesService.findAllByProductId(model.getId());
			model.setListImage(listImage);
			
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.setAttribute("productModel", model);

			request.getRequestDispatcher(getURLPage(request.getServletPath())).forward(request, response);
		} else {
			doGet(request, response);
		}

	}

	private String getURLPage(String type) {
		String url = null;
		type = Utils.getUrlPatterns(type);
		if (type.equalsIgnoreCase("admin-product-add")) {
			url = "view/admin/product/add.jsp";
		} else if (type.equalsIgnoreCase("admin-product-list")) {
			url = "view/admin/product/list.jsp";
		} else if (type.equalsIgnoreCase("admin-product-edit")) {
			url = "view/admin/product/edit.jsp";
		} else if (type.equalsIgnoreCase("admin-product-view")) {
			url = "view/admin/product/edit.jsp";
		} else if (type.equalsIgnoreCase("admin-product-getBrand")) {
			url = "view/admin/product/get_brand.jsp";
		}
		if (type.equalsIgnoreCase("admin-product-getClassify")) {
			url = "view/admin/product/get_classify.jsp";
		}
		return url;
	}

}

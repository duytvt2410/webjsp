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

import model.CategoryModel;
import model.ImagesModel;
import model.ProductClassifyModel;
import model.ProductModel;
import model.UserModel;
import service.IBrandService;
import service.ICategoryService;
import service.IClassifyService;
import service.IProductClassifyService;
import service.IProductService;
import service.imp.BrandService;
import service.imp.CategoryService;
import service.imp.ClassifyService;
import service.imp.ProductClassifyService;
import service.imp.ProductService;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/admin-product-add", "/admin-product-list", "/admin-product-edit", "/admin-product-view",
		"/admin-product-getBrand", "/admin-product-getClassify", "/admin-product-delete", "/admin-product-updateIsNew" })
public class ProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String option = null;

	private String url = null;

	private boolean isUseSendRedirect, isDelete;

	private ProductModel model;

	private ICategoryService categoryService = CategoryService.getInstance();

	private IBrandService brandService = BrandService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	private IProductService productServie = ProductService.getInstance();
	
	private IProductClassifyService productClassiyServie = ProductClassifyService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		isUseSendRedirect = false;
		isDelete = false;
		url = request.getRequestURI();

		if (url.startsWith(request.getContextPath() + "/admin-product-add")) {
			getPageAdd(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-product-list")) {
			getPageList(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-product-edit")) {
			getPageEdit(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-product-view")) {
			getPageView(request);
		} else if(url.startsWith(request.getContextPath() + "/admin-product-getBrand")) { 
			getPageBrand(request);
		} else if(url.startsWith(request.getContextPath() + "/admin-product-getClassify")) { 
			getPageClassify(request);
		} else if (url.startsWith(request.getContextPath() + "/admin-product-delete") && request.getParameter("option") != null) {
			doPost(request, response);
			isDelete = true;
		} else if (url.startsWith(request.getContextPath() + "/admin-product-delete")) {
			isUseSendRedirect = true;
		} else if(url.startsWith(request.getContextPath() + "/admin-product-updateIsNew")) {
			String id = request.getParameter("id");
			String isNew = request.getParameter("isNew");
			productServie.updateIsNew(isNew, id);
			isDelete = true;
		}
		if (isUseSendRedirect == true) {
			response.sendRedirect(request.getContextPath() + "/admin-product-list");
		} else if(isDelete == false){
			request.getRequestDispatcher(url).forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		option = request.getParameter("option");
		model = getModelFromForm(request);
		isUseSendRedirect = false;
		if (option != null) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
			Map<String, String> map = null;
			if (option.equalsIgnoreCase("add")) {
				model.setCreateBy(user.getId());
				map = addProcess(request);
			} else if (option.equalsIgnoreCase("edit")) {
				model.setUpdateBy(user.getId());
				map = editProcess(request);
			} else if (option.equalsIgnoreCase("delete")) {
				map = deleteProcess(request);
			}
			Set<String> set = map.keySet();
			String alert = "";
			String message = "";
			for (String key : set) {
				alert = key;
				message = map.get(key);
			}

			request.setAttribute("alert", alert);
			request.setAttribute("message", message);
			request.setAttribute("productModel", model);
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			request.getRequestDispatcher(url).forward(request, response);

		} else {
			doGet(request, response);
		}

	}
	private ProductModel getModelFromForm(HttpServletRequest request) {
		ProductModel model = new ProductModel();

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return null;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return null;
			}

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
						model.setId(fileItem.getString());
					} else if (fileItem.getFieldName().equals("category")) {
						model.setCategoryAlias((fileItem.getString()));
					} else if (fileItem.getFieldName().equals("brand")) {
						model.setBrandId(fileItem.getString());
					} else if (fileItem.getFieldName().equals("status")) {
						model.setStatus(fileItem.getString());
					} else if (fileItem.getFieldName().equals("classify")) {

						ProductClassifyModel classify = new ProductClassifyModel();
						classify.setClassifyId(fileItem.getString());
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
					} else if (fileItem.getFieldName().equals("file-image")) {
						if(fileItem.getString() != null ? !fileItem.getString().equals("") : fileItem.getString() != null) {
							ImagesModel img = new ImagesModel();
							img.setPhoto(fileItem.getString());
							model.setImageProduct(img);
						}
					} else if (fileItem.getFieldName().equals("file-images")) {
						if(fileItem.getString() != null ? !fileItem.getString().equals("") : fileItem.getString() != null) {
							String imgs[] = fileItem.getString().split("dt14082410dt");
							List<ImagesModel> lstImgs = new ArrayList<ImagesModel>();
							for(String s : imgs) {
								ImagesModel img = new ImagesModel();
								img.setPhoto(s);
								lstImgs.add(img);
							}
						model.setImagesDetail(lstImgs);
						}
					}

				} 
			}
			model.setListClassify(listClassify);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	private void getPageAdd(HttpServletRequest request) {
		request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
		this.url = SystemContain.URL_PAGE_PRODUCT_ADD;
	}

	private void getPageList(HttpServletRequest request) {
		List<CategoryModel> listCategory = categoryService.findAllByStatus("active");
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String status = request.getParameter("status");
		if (brand != null && status != null) {
			request.setAttribute("category", category);
			request.setAttribute("brand", brand);
			request.setAttribute("status", status);
		
			request.setAttribute("listProduct", productServie.findAllByBrandIdAndStatus(brand, status));
			
		} else {
			if (listCategory.size() != 0) {
				request.setAttribute("category", listCategory.get(0).getAlias());
				String brandId = brandService.findAllByCategoryAliasAndStatus(listCategory.get(0).getAlias(), "active").get(0).getId();
				request.setAttribute("brand", brandId);
				request.setAttribute("status", "active");
				request.setAttribute("listProduct", productServie.findAllByBrandIdAndStatus(brandId, "active"));
			} else {
				request.setAttribute("listProduct", productServie.findAllByBrandIdAndStatus("", "active"));
			}
		}
		request.setAttribute("listCategory", listCategory);
		url = SystemContain.URL_PAGE_PRODUCT_LIST;
	}

	private void getPageEdit(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id != null) {
			request.setAttribute("listCategory", categoryService.findAllByStatus("active"));
			try {
				ProductModel productModel = productServie.findOneById(id);
				
				productModel.setListClassify(productClassiyServie.findAllByProductId(id));
				
				request.setAttribute("productModel", productModel);
				
			} catch (Exception e) {
				isUseSendRedirect = true;
			}
		} else {
			isUseSendRedirect = true;
		}
		url = SystemContain.URL_PAGE_PRODUCT_EDIT;
	}

	private void getPageView(HttpServletRequest request) {
		ProductModel productModel = productServie.findOneById(request.getParameter("id"));
		productModel.setListClassify(productClassiyServie.findAllByProductId(productModel.getId()));
		request.setAttribute("productModel", productModel);
		url = SystemContain.URL_PAGE_PRODUCT_VIEW;
	}
	
	private void getPageBrand(HttpServletRequest request) {
		request.setAttribute("brand", request.getParameter("brand"));
		request.setAttribute("listBrand", brandService.findAllByCategoryAlias(request.getParameter("category")));
		url = "view/admin/product/get_brand.jsp";
	}
	
	private void getPageClassify(HttpServletRequest request) {
		request.setAttribute("classify", request.getParameter("classify"));
		request.setAttribute("listClassify",
				classifyService.findAllByCategoryAlias(request.getParameter("category")));
		url = "view/admin/product/get_classify.jsp";
	}

	private Map<String, String> deleteProcess(HttpServletRequest request) {
		url = "view/admin/delete.jsp";
		String id [] = request.getParameter("id").split(",");
		if(id!= null && id.length == 1) {
			return productServie.delete(id[0]);
		}
		else {
			return productServie.deleteAll(id);
		}
	}

	private Map<String, String> addProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_PRODUCT_ADD;
		return productServie.insert(model);
	}

	private Map<String, String> editProcess(HttpServletRequest request) {
		url = SystemContain.URL_PAGE_PRODUCT_EDIT;
		return productServie.update(model, model.getId());
	}
	
	public static void main(String[] args) {
//		ArrayList<String> product = new ArrayList<String>();
//		for(int i = 0; i < 16; i++) {
//			product.add("product " + (i+1));
//		}
//		int len = product.size();
//		int limit = 6;
//		int first = 13;
//		int end = first + (len - first) ;
//		if(end - limit - first > 0) end = first + limit - 1;
//		System.out.println(first + " " + end);
//		for(int i = first - 1; i <= end -1; i++) {
//			System.out.println(product.get(i));
//		}

		
	}

}

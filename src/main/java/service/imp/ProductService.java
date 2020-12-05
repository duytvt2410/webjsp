package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IProductDAO;
import dao.imp.ProductDAO;
import model.ImagesModel;
import model.ProductClassifyModel;
import model.ProductModel;
import service.IBrandService;
import service.IClassifyService;
import service.IProductClassifyService;
import service.IProductService;
import service.InterfaceImagesService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class ProductService implements IProductService {

	private IBrandService brandService = BrandService.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

	private IProductDAO productDAO = ProductDAO.getInstance();

	private IProductClassifyService productClassifyService = ProductClassifyService.getInstance();

	private InterfaceImagesService imageService = ImagesService.getInstance();

	private static ProductService service = null;

	public static ProductService getInstance() {
		if (service == null) {
			service = new ProductService();
		}
		return service;
	}

	@Override
	public List<ProductModel> findAll() {
		return productDAO.findAll();
	}

	@Override
	public List<ProductModel> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductModel findOneByAlias(String alias) {
		return productDAO.findOneByAlias(alias);
	}

	@Override
	public ProductModel findOneById(String id) {
		return productDAO.findOneById(id);
	}

	@Override
	public Map<String, String> insert(ProductModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm có chứa ký tự đặc biệt");
			return map;
		}
		if (checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Độ dài tên chỉ được từ 2 đến 100 ký tự");
			return map;
		}

		model.setAlias(Utils.formatAlias(model.getName()));

		if (findOneByAlias(model.getAlias()) != null) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm đã tồn tại");
			return map;
		}

		if (brandService.findOneById(model.getBrandId()) == null) {
			map.put("danger", "Tên nhãn không tồn tại");
			return map;
		}
		if (model.getPromotionInformation() == null)
			model.setPromotionInformation("");

		if (model.getDescription() == null)
			model.setDescription("");

		if (model.getSpecifications() == null)
			model.setSpecifications("");

		if (Valid.isOverLength(model.getPromotionInformation(), 0, 15000)) {
			if (model.getPromotionInformation().length() == 0) {
				model.setPromotionInformation("");
			} else {
				map.put("danger", "Thông tin khuyên mãi quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getDescription(), 0, 15000)) {
			if (model.getDescription().length() == 0) {
				model.setDescription("");
			} else {
				map.put("danger", "Bài viết mô tả quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getSpecifications(), 0, 15000)) {
			if (model.getSpecifications().length() == 0) {
				model.setSpecifications("");
			} else {
				map.put("danger", "Thông số kỹ thuật quá dài");
				return map;
			}

		}

		for (ProductClassifyModel product : model.getListClassify()) {
			if (classifyService.findOneById(product.getClassifyId()) == null) {
				map.put("danger", "Nhóm sản phẩm không tồn tại");
				return map;
			}
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		model.setStatus("active");
		model.setCreateDate(timestamp);
		model.setUpdateDate(null);
		model.setUpdateBy("");

		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);

		id += String.valueOf(new Date().getTime());
		model.setId(id);

		if (productDAO.insert(model) == true) {

			try {
				if (model.getImageProduct() != null) {

					ImagesModel imgProduct = new ImagesModel();
					imgProduct.setProduct_Id(model.getId());
					imgProduct.setType("image_product");
					imgProduct.setName("img-1");
					imgProduct.setPhoto(model.getImageProduct().getPhoto());
					imageService.insert(imgProduct);

				}
				if (model.getImagesDetail() != null) {
					int index_img = 2;
					for (ImagesModel img : model.getImagesDetail()) {

						ImagesModel imgDetailProduct = new ImagesModel();
						imgDetailProduct.setType("image_detail");
						imgDetailProduct.setProduct_Id(model.getId());
						imgDetailProduct.setName("img-" + index_img);
						imgDetailProduct.setPhoto(img.getPhoto());
						imageService.insert(imgDetailProduct);
						index_img++;

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (ProductClassifyModel productClassify : model.getListClassify()) {

				productClassify.setProductId(model.getId());
				productClassify.setProductAlias(model.getAlias());

				productClassifyService.insert(productClassify);
			}

			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public Map<String, String> update(ProductModel model, String id) {
		Map<String, String> map = new HashMap<String, String>();
		ProductModel oldModel = findOneById(model.getId());

		if (oldModel == null) {
			map.put("danger", "Sản phẩm không tồn tại.");
			return map;
		}

		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm không nên có ký tự đặc biệt");
			return map;
		}
		if (checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Độ dài tên chỉ được từ 2 đến 100 ký tự");
			return map;
		}

		model.setAlias(Utils.formatAlias(model.getName()));

		if (findOneByAlias(model.getAlias()) != null && !model.getAlias().equals(oldModel.getAlias())) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm đã tồn tại");
			return map;
		}

		if (brandService.findOneById(model.getBrandId()) == null) {
			map.put("danger", "Tên nhãn không tồn tại");
			return map;
		}
		if (model.getPromotionInformation() == null)
			model.setPromotionInformation("");

		if (model.getDescription() == null)
			model.setDescription("");

		if (model.getSpecifications() == null)
			model.setSpecifications("");

		if (Valid.isOverLength(model.getPromotionInformation(), 0, 15000)) {
			if (model.getPromotionInformation().length() == 0) {
				model.setPromotionInformation("");
			} else {
				map.put("danger", "Thông tin khuyên mãi quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getDescription(), 0, 15000)) {
			if (model.getDescription().length() == 0) {
				model.setDescription("");
			} else {
				map.put("danger", "Bài viết mô tả quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getSpecifications(), 0, 15000)) {
			if (model.getSpecifications().length() == 0) {
				model.setSpecifications("");
			} else {
				map.put("danger", "Thông số kỹ thuật quá dài");
				return map;
			}

		}

		for (ProductClassifyModel product : model.getListClassify()) {
			if (classifyService.findOneById(product.getClassifyId()) == null) {
				map.put("danger", "Nhóm sản phẩm không tồn tại");
				return map;
			}
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		if (model.getStatus() == null)
			model.setStatus(oldModel.getStatus());
		model.setUpdateDate(timestamp);

		if (productDAO.update(model, id) == true) {

			try {

				if (model.getImageProduct() != null) {

					if (imageService.deleteByProductIdAndType(model.getId(), "image_product") == true) {
					
							ImagesModel imgProduct = new ImagesModel();
							imgProduct.setProduct_Id(model.getId());
							imgProduct.setType("image_product");
							imgProduct.setName("img-1");
							imgProduct.setPhoto(model.getImageProduct().getPhoto());
							imageService.insert(imgProduct);

					}

				}

				if (model.getImagesDetail() != null) {
					if (imageService.deleteByProductIdAndType(model.getId(), "image_detail") == true) {
						int index_img = 2;
						for (ImagesModel img : model.getImagesDetail()) {
							
								ImagesModel imgDetailProduct = new ImagesModel();
								imgDetailProduct.setType("image_detail");
								imgDetailProduct.setName("img-" + index_img);
								imgDetailProduct.setProduct_Id(model.getId());
								imgDetailProduct.setPhoto(img.getPhoto());
								imageService.insert(imgDetailProduct);
								index_img++;
							

						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (productClassifyService.deleteByProductId(model.getId())) {
				for (ProductClassifyModel productClassify : model.getListClassify()) {

					productClassify.setProductId(model.getId());
					productClassify.setProductAlias(model.getAlias());

					productClassifyService.insert(productClassify);
				}

			}

			map.put("success", "Sửa thành công.");
			return map;
		} else {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại");
			return map;
		}
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status) {
		return productDAO.findAllByCategoryAndStatusAndPricePromotion(categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories,
			String status) {
		return productDAO.findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(isAccessories, status);
	}

	@Override
	public List<ProductModel> findAllRelatedProduct(String brandId, String idProduct, String status) {
		return productDAO.findAllRelatedProduct(brandId, idProduct, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status) {
		return productDAO.findAllByCategoryAndStatus(categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, int limit,
			int start, Object... parameter) {
		return productDAO.findAllCustomCondition(condition, categoryAlias, status, limit, start, parameter);
	}

	@Override
	public Map<String, String> delete(String id) {
		Map<String, String> map = new HashMap<String, String>();
		imageService.deleteByProductIdAndType(id, "image_product");
		imageService.deleteByProductIdAndType(id, "image_detail");

		productClassifyService.deleteByProductId(id);

		if (productDAO.delete(id)) {
			map.put("success", "Xóa thành công");
		} else {
			ProductModel product = productDAO.findOneById(id);
			map.put("danger", product.getName() + " có chứa các nhãn hàng và nhóm nhóm sản phẩm. Không thể xóa");
		}

		return map;
	}

	@Override
	public Map<String, String> deleteAll(String[] id) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess = true;
		String modelFail = "";
		for (String i : id) {
			if (!productDAO.delete(i)) {
				ProductModel product = productDAO.findOneById(i);
				modelFail += product.getName() + ", ";
				isSuccess = false;
			}
		}
		if (isSuccess == true) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", modelFail + " có chứa các nhãn hàng và nhóm nhóm sản phẩm. Không thể xóa");
		}
		return map;
	}

	@Override
	public List<ProductModel> findAllByBrandIdAndStatus(String brandId, String status) {
		return productDAO.findAllByBrandIdAndStatus(brandId, status);
	}

	@Override
	public boolean updateQty(int qty, String id) {
		return productDAO.updateQty(qty, id);
	}

	@Override
	public int countAllCustomCondition(String condition, String categoryAlias, String status, Object... parameter) {

		return productDAO.countAllCustomCondition(condition, categoryAlias, status, parameter);
	}

	@Override
	public List<ProductModel> findAllByKeyword(String condition, Object... keyParameter) {

		return productDAO.findAllByKeyword(condition, keyParameter);
	}

	@Override
	public boolean updateRated(double rated, String id) {
		return productDAO.updateRated(rated, id);
	}

	@Override
	public boolean updateIsNew(String isNew, String id) {
		return productDAO.updateIsNew(isNew, id);
	}

	@Override
	public List<ProductModel> findAllByCategoryAndIsNewAndStatusAndLimit(String categoryAlias, String isNew,
			String status, int limit) {
		return productDAO.findAllByCategoryAndIsNewAndStatusAndLimit(categoryAlias, isNew, status, limit);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit(String is_accessories,
			String isNew, String status, int limit) {
		return productDAO.findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit(is_accessories, isNew, status, limit);
	}

	@Override
	public int countAll() {
		return productDAO.countAll();
	}

	@Override
	public int countAllByCategory(String categoryAlias) {
		return productDAO.countAllByCategory(categoryAlias);
	}

}

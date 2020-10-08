package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;

import dao.IProductDAO;
import dao.imp.ProductDAO;
import model.BrandModel;
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
	public List<ProductModel> findAllByIsAccessories(String isAccessories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductModel findOneByAlias(String alias) {
		return productDAO.findOneByAlias(alias);
	}

	@Override
	public ProductModel findOneById(Long id) {
		return productDAO.findOneById(id);
	}

	@Override
	public Map<String, String> insert(ProductModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm chỉ được chứa các ký tự: \" ,.:/)(/ \"");
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

		BrandModel brand = null;

		if (brandService.findOneById(model.getBrandId()) == null) {
			map.put("danger", "Tên nhãn không tồn tại");
			return map;
		} else {
			brand = brandService.findOneById(model.getBrandId());
		}
		if (model.getPromotionInformation() == null)
			model.setPromotionInformation("");

		if (model.getDescription() == null)
			model.setDescription("");

		if (model.getSpecifications() == null)
			model.setSpecifications("");

		if (Valid.isOverLength(model.getPromotionInformation(), 0, 10000)) {
			if (model.getPromotionInformation().length() == 0) {
				model.setPromotionInformation("");
			} else {
				map.put("danger", "Thông tin khuyên mãi quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getDescription(), 0, 10000)) {
			if (model.getDescription().length() == 0) {
				model.setDescription("");
			} else {
				map.put("danger", "Bài viết mô tả quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getSpecifications(), 0, 10000)) {
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

		if (model.getFieldsImageProduct().getSize() == 0) {
			map.put("danger", "Không có ảnh cho sản phẩm");
			return map;
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		model.setStatus("active");
		model.setCreateDate(timestamp);
		model.setCreateBy("admin");
		model.setUpdateDate(null);
		model.setUpdateBy("");
		
		

		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);

		id += String.valueOf(new Date().getTime());
		model.setId(Long.parseLong(id));

		if (productDAO.insert(model) == true) {

			try {
				ImagesModel imageProduct = new ImagesModel();
				imageProduct = new ImagesModel();
				if (model.getFieldsImageProduct().getSize() > 0) {
					String mimetype = model.getFieldsImageProduct().getContentType();
					String type = mimetype.split("/")[0];

					if (type.equals("image")) {
						imageProduct.setProduct_Id(model.getId());
						imageProduct.setType("image_product");
						imageProduct.setInputImage(model.getFieldsImageProduct().getInputStream());
					}
				}
				
				imageService.insert(imageProduct);


				if (model.getFieldsImagesDetail().size() > 0) {
					String mimetype = model.getFieldsImageProduct().getContentType();
					String type = mimetype.split("/")[0];
					for (FileItem images : model.getFieldsImagesDetail()) {
						if (type.equals("image")) {
							ImagesModel image = new ImagesModel();
							image.setType("image_detail");
							image.setProduct_Id(model.getId());
							image.setInputImage(images.getInputStream());
							imageService.insert(image);
						}

					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	public Map<String, String> update(ProductModel model, Long id) {
		Map<String, String> map = new HashMap<String, String>();
		ProductModel oldModel = findOneById(model.getId());

		if (oldModel == null) {
			map.put("danger", "Sản phẩm không tồn tại.");
			return map;
		}

		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên sản phẩm không hợp lệ. Tên sản phẩm chỉ được chứa các ký tự: \" ,.:/)(/ \"");
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

		BrandModel brand = null;

		if (brandService.findOneById(model.getBrandId()) == null) {
			map.put("danger", "Tên nhãn không tồn tại");
			return map;
		} else {
			brand = brandService.findOneById(model.getBrandId());
		}
		if (model.getPromotionInformation() == null)
			model.setPromotionInformation("");

		if (model.getDescription() == null)
			model.setDescription("");

		if (model.getSpecifications() == null)
			model.setSpecifications("");

		if (Valid.isOverLength(model.getPromotionInformation(), 0, 10000)) {
			if (model.getPromotionInformation().length() == 0) {
				model.setPromotionInformation("");
			} else {
				map.put("danger", "Thông tin khuyên mãi quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getDescription(), 0, 10000)) {
			if (model.getDescription().length() == 0) {
				model.setDescription("");
			} else {
				map.put("danger", "Bài viết mô tả quá dài");
				return map;
			}
		}

		if (Valid.isOverLength(model.getSpecifications(), 0, 10000)) {
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
		model.setUpdateBy("admin");

		if (productDAO.update(model, id) == true) {

			try {
				ImagesModel imageProduct = new ImagesModel();
				imageProduct = new ImagesModel();
				if (model.getFieldsImageProduct() != null) {

					if (imageService.deleteByProductIdAndType(model.getId(), "image_product") == true) {
						String mimetype = model.getFieldsImageProduct().getContentType();
						String type = mimetype.split("/")[0];

						if (type.equals("image")) {
							imageProduct.setProduct_Id(model.getId());
							imageProduct.setType("image_product");
							imageProduct.setInputImage(model.getFieldsImageProduct().getInputStream());
						}
						imageService.insert(imageProduct);

					}

				}

				if (model.getFieldsImagesDetail().size() > 0) {
					if (imageService.deleteByProductIdAndType(model.getId(), "image_detail") == true) {
						
						for (FileItem images : model.getFieldsImagesDetail()) {
							String mimetype = images.getContentType();
							String type = mimetype.split("/")[0];
							if (type.equals("image")) {
								ImagesModel image = new ImagesModel();
								image.setType("image_detail");
								image.setProduct_Id(model.getId());
								image.setInputImage(images.getInputStream());
								imageService.insert(image);
								
							}

						}
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(productClassifyService.deleteByProductId(model.getId())) {
				for (ProductClassifyModel productClassify : model.getListClassify()) {
				
					productClassify.setProductId(model.getId());
					productClassify.setProductAlias(model.getAlias());

					productClassifyService.insert(productClassify);
				}
				
			}

			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatusAndOrderByCreateDate(String categoryAlias, String status) {
		return productDAO.findAllByCategoryAndStatusAndOrderByCreateDate(categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndOrderByCreateDate(String isAccessories,String status) {
			return productDAO.findAllByCategoryIsAccessoriesAndStatusAndOrderByCreateDate(isAccessories, status);
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
	public List<ProductModel> findAllRelatedProduct(Long brandId, Long idProduct, String status) {
		return productDAO.findAllRelatedProduct(brandId,idProduct , status);
	}
	@Override
	public List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status) {
		return productDAO.findAllByCategoryAndStatus(categoryAlias, status);
	}


	@Override
	public List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status,
			Object... parameter) {
		return productDAO.findAllCustomCondition(condition, categoryAlias, status, parameter);
	}

}

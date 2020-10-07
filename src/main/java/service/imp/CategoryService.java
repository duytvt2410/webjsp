package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ICategoryDAO;
import dao.imp.CategoryDAO;
import model.CategoryModel;
import service.ICategoryService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class CategoryService implements ICategoryService{
	
	private ICategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	private static CategoryService service = null;

	public static CategoryService getInstance() {
		if (service == null) {
			service = new CategoryService();
		}
		return service;
	}


	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDAO.findAll();
	}

	@Override
	public Map<String, String> insert(CategoryModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getName(), 2, 30);
		
		if(checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên danh mục không hợp lệ. Không được chứa ký tự đặc biệt");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên danh mục không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
			return map;
		}
		if(checkName.equals(SystemContain.CONTAINS_DIGIT)) {
			map.put("danger", "Tên danh mục không hợp lệ. Không được chứa số trong tên");
			return map;
		}
		
		model.setAlias(Utils.formatAlias(model.getName()));
		
		if(findOneByAlias(model.getAlias()) != null) {
			map.put("danger", "Tên danh mục không hợp lệ. Tên danh mục đã tồn tại");
			return map;
		}
		
		if(model.getIsAccessories() == null) model.setIsAccessories("no");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		model.setStatus("active");
		model.setCreateDate(timestamp);
		model.setCreateBy("admin");
		model.setUpdateDate(null);
		model.setUpdateBy("");
		Date id = new Date();
		model.setId(id.getTime());
		
		if(categoryDAO.insert(model) == true) {
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public CategoryModel findOneByAlias(String alias) {
		return categoryDAO.findOneByAlias(alias);
	}

	@Override
	public Map<String, String> update(CategoryModel model, Long id) {
		Map<String, String> map = new HashMap<String, String>();
		CategoryModel oldModel = findOneById(id);
		
		if(oldModel == null) {
			map.put("danger", "Danh mục không tồn tại.");
			return map;
		}
		
		String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getName(), 2, 30);
		if(checkName.equals(SystemContain.EMPTY)) {
			map.put("danger", "Tên danh mục không hợp lệ. Không được bỏ trống");
			return map;
		}
		
		if(checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên danh mục không hợp lệ. Không được chứa ký tự đặc biệt");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên danh mục không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
			return map;
		}
		if(checkName.equals(SystemContain.CONTAINS_DIGIT)) {
			map.put("danger", "Tên danh mục không hợp lệ. Không được chứa số trong tên");
			return map;
		}
		
		model.setAlias(Utils.formatAlias(model.getName()));
		
		if(findOneByAlias(model.getAlias()) != null && !findOneByAlias(model.getAlias()).getAlias().equalsIgnoreCase(oldModel.getAlias())) {
			map.put("danger", "Tên danh mục không hợp lệ. Tên danh mục đã tồn tại");
			return map;
		}
		
		if(model.getIsAccessories() == null) model.setIsAccessories("no");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(model.getStatus() == null) model.setStatus(oldModel.getStatus());
		model.setUpdateDate(timestamp);
		model.setUpdateBy("admin");
		
		if(categoryDAO.update(model, id) == true) {
			map.put("success", "Sửa thành công.");
			return map;
		} else {
			map.put("danger", "Sửa thất bại.");
			return map;
		}
	}

	@Override
	public CategoryModel findOneById(Long id) {
		return categoryDAO.findOneById(id);
	}

	@Override
	public List<CategoryModel> findAllByIsAccessories(String isAccessories) {
		return categoryDAO.findAllByIsAccessories(isAccessories);
	}

	@Override
	public List<CategoryModel> findAllByStatus(String status) {
		return categoryDAO.findAllByStatus(status);
	}

	@Override
	public List<CategoryModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status) {
		if(status == null || status.equalsIgnoreCase("all")) {
			return findAllByIsAccessories(isAccessories);
		}
		
		if(isAccessories == null || isAccessories.equalsIgnoreCase("all")) {
			return findAllByStatus(status);
		}
		return categoryDAO.findAllByIsAccessoriesAndStatus(isAccessories, status);
	}


	@Override
	public Map<String, String> delete(Long id) {
		Map<String, String> map = new HashMap<String, String>();
		if(categoryDAO.delete(id)) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", "Danh mục có chứa các nhãn hàng và nhóm nhóm sản phẩm. Không thể xóa");
		}
		
		return map;
	}
	
	

}

package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IClassifyDAO;
import dao.imp.ClassifyDAO;
import model.ClassifyModel;
import service.ICategoryService;
import service.IClassifyService;
import service.IProductClassifyService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class ClassifyService implements IClassifyService{
	
	private IClassifyDAO classifyDAO = ClassifyDAO.getInstance();
	
	private IProductClassifyService productClassify = ProductClassifyService.getInstance();
	
	private ICategoryService categoryService = CategoryService.getInstance();

	private static ClassifyService service = null;

	public static ClassifyService getInstance() {
		if (service == null) {
			service = new ClassifyService();
		}
		return service;
	}

	
	@Override
	public List<ClassifyModel> findAll() {
		return classifyDAO.findAll();
	}

	@Override
	public List<ClassifyModel> findAllByCategoryId(String id) {
		return null;
	}

	@Override
	public List<ClassifyModel> findAllByStatus(String status) {
		
		return classifyDAO.findAllByStatus(status);
	}

	@Override
	public ClassifyModel findOneByAlias(String alias) {
		
		return classifyDAO.findOneByAlias(alias);
	}

	@Override
	public ClassifyModel findOneById(String id) {
		
		return classifyDAO.findOneById(id);
	}

	@Override
	public Map<String, String> insert(ClassifyModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);
		
		if(checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Tên nhóm chỉ được chứa các ký tự: \" ,.:/)(/ \"");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Độ dài tên chỉ được từ 2 đến 100 ký tự");
			return map;
		}
		
		model.setAlias(model.getCategoryAlias() + "-" + Utils.formatAlias(model.getName()));
		
		if(findOneByAlias(model.getAlias()) != null) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Tên nhóm sản phẩm đã tồn tại");
			return map;
		}
		
		if(model.getCategoryAlias() != null) {
			if (categoryService.findOneByAlias(model.getCategoryAlias()) != null){
				model.setCategoryId(categoryService.findOneByAlias(model.getCategoryAlias()).getId());
			} else {
				map.put("danger", "Danh mục không tồn tại.");
				return map;
			}
		} else {
			map.put("danger", "Danh mục không tồn tại.");
			return map;
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
		
		if(classifyDAO.insert(model) == true) {
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public Map<String, String> update(ClassifyModel model, String id) {
		Map<String, String> map = new HashMap<String, String>();
		ClassifyModel oldModel = findOneById(id);
		
		if(oldModel == null) {
			map.put("danger", "Nhóm sản phẩm không tồn tại.");
			return map;
		}
		
		String checkName = Valid.checkNameNotContainSpecial(model.getName(), 2, 100);
		
		if(checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Tên nhóm chỉ được chứa các ký tự: \" ,.:/)(/ \"");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Độ dài tên chỉ được từ 2 đến 100 ký tự");
			return map;
		}
		
		model.setAlias(model.getCategoryAlias() + "-" + Utils.formatAlias(model.getName()));
		
		if(findOneByAlias(model.getAlias()) != null&& !findOneByAlias(model.getAlias()).getAlias().equalsIgnoreCase(oldModel.getAlias())) {
			map.put("danger", "Tên nhóm sản phẩm không hợp lệ. Tên nhóm sản phẩm đã tồn tại");
			return map;
		}
		
		if(model.getCategoryAlias() != null) {
			if (categoryService.findOneByAlias(model.getCategoryAlias()) != null){
				model.setCategoryId(categoryService.findOneByAlias(model.getCategoryAlias()).getId());
			} else {
				map.put("danger", "Danh mục không tồn tại.");
				return map;
			}
		} else {
			map.put("danger", "Danh mục không tồn tại.");
			return map;
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(model.getStatus() == null) model.setStatus(oldModel.getStatus());
		model.setUpdateDate(timestamp);
		
		if(classifyDAO.update(model, model.getId()) == true) {
			map.put("success", "Sửa thành công.");
			return map;
		} else {
			map.put("danger", "Sửa thất bại.");
			return map;
		}
	}

	@Override
	public List<ClassifyModel> findAllByCategoryAlias(String categoryAlias) {
		
		return classifyDAO.findAllByCategoryAlias(categoryAlias);
	}

	@Override
	public List<ClassifyModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status) {
		if(status == null || status.equalsIgnoreCase("all")) {
			return findAllByCategoryAlias(categoryAlias);
		}
		
		if(categoryAlias == null || categoryAlias.equalsIgnoreCase("all")) {
			return findAllByStatus(status);
		}
 		return classifyDAO.findAllByCategoryAliasAndStatus(categoryAlias, status);
	}


	@Override
	public Map<String, String> delete(String id) {
		Map<String, String> map = new HashMap<String, String>();
		if(productClassify.findAllByClassifyId(id) != null) {
			ClassifyModel model = classifyDAO.findOneById(id);
			map.put("danger", model.getName() + " có liên kết với sản phẩm. Không thể xóa");
		} else {
			if(classifyDAO.delete(id)) {
				map.put("success", "Xóa thành công");
			} else {
				map.put("danger"," Thất bại. Vui lòng thử lại");
			}
		}
		
		
		return map;
	}


	@Override
	public Map<String, String> deleteAll(String[] id) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess = true;
		String modelFail = "";
		for(String i : id ) {
			if(productClassify.findAllByClassifyId(i) != null) {
				ClassifyModel model = classifyDAO.findOneById(i);
				modelFail += model.getName() + ", ";
				isSuccess = false;
			} else {
				if(classifyDAO.delete(i)) {
					map.put("success", "Xóa thành công");
				} else {
					map.put("danger"," Thất bại. Vui lòng thử lại");
				}
			}
		}
		if(isSuccess == true) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", modelFail +  " có liên kết với sản phẩm. Không thể xóa");
		}
		return map;
	}

}

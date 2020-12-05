package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.IBrandDAO;
import dao.imp.BrandDAO;
import model.BrandModel;
import service.IBrandService;
import service.ICategoryService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class BrandService implements IBrandService {

	private IBrandDAO brandDAO = BrandDAO.getInstance();

	private ICategoryService categoryService = CategoryService.getInstance();

	private static BrandService service = null;

	public static BrandService getInstance() {
		if (service == null) {
			service = new BrandService();
		}
		return service;
	}

	@Override
	public List<BrandModel> findAll() {
		return brandDAO.findAll();
	}

	@Override
	public List<BrandModel> findAllByCategoryId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandModel> findAllByStatus(String status) {

		return brandDAO.findAllByStatus(status);
	}

	@Override
	public BrandModel findOneByAlias(String alias) {

		return brandDAO.findOneByAlias(alias);
	}

	@Override
	public BrandModel findOneById(String id) {

		return brandDAO.findOneById(id);
	}

	@Override
	public Map<String, String> insert(BrandModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getName(), 2, 100);
		if (checkName.equals(SystemContain.EMPTY)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được bỏ trống");
			return map;
		}

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được chứa ký tự đặc biệt");
			return map;
		}
		if (checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
			return map;
		}
		if (checkName.equals(SystemContain.CONTAINS_DIGIT)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được chứa số trong tên");
			return map;
		}

		model.setAlias(model.getCategoryAlias() + "-" + Utils.formatAlias(model.getName()));

		if (model.getCategoryAlias() != null) {
			if (findOneByCategoryAliasAndAlias(model.getCategoryAlias(), model.getAlias()) != null) {
				map.put("danger", "Tên nhãn hàng không hợp lệ. Tên nhãn hàng đã tồn tại");
				return map;
			}
			if (categoryService.findOneByAlias(model.getCategoryAlias()) != null) {
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

		Date id = new Date();
		model.setId(""+id.getTime());

		if (brandDAO.insert(model) == true) {
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public Map<String, String> update(BrandModel model, String id) {
		Map<String, String> map = new HashMap<String, String>();
		BrandModel oldModel = findOneById(id);

		if (oldModel == null) {
			map.put("danger", "Nhãn hàng không tồn tại.");
			return map;
		}

		String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getName(), 2, 100);
		if (checkName.equals(SystemContain.EMPTY)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được bỏ trống");
			return map;
		}

		if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được chứa ký tự đặc biệt");
			return map;
		}
		if (checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
			return map;
		}
		if (checkName.equals(SystemContain.CONTAINS_DIGIT)) {
			map.put("danger", "Tên nhãn hàng không hợp lệ. Không được chứa số trong tên");
			return map;
		}

		model.setAlias(model.getCategoryAlias() + "-" + Utils.formatAlias(model.getName()));

		if (findOneByAlias(model.getAlias()) != null
				&& !findOneByAlias(model.getAlias()).getAlias().equalsIgnoreCase(oldModel.getAlias())) {
			map.put("danger", "Tên nhãn không hợp lệ. Tên nhóm sản phẩm đã tồn tại");
			return map;
		}
		if (model.getCategoryAlias() != null) {

			if (categoryService.findOneByAlias(model.getCategoryAlias()) != null) {
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

		if (model.getStatus() == null)
			model.setStatus(oldModel.getStatus());
		model.setUpdateDate(timestamp);

		if (brandDAO.update(model, id) == true) {
			map.put("success", "Sửa thành công.");
			return map;
		} else {
			map.put("danger", "Sửa thất bại.");
			return map;
		}
	}

	@Override
	public List<BrandModel> findAllByCategoryAlias(String categoryAlias) {

		return brandDAO.findAllByCategoryAlias(categoryAlias);
	}

	@Override
	public List<BrandModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status) {
		if (status == null || status.equalsIgnoreCase("all")) {
			return findAllByCategoryAlias(categoryAlias);
		}

		if (categoryAlias == null || categoryAlias.equalsIgnoreCase("all")) {
			return findAllByStatus(status);
		}
		return brandDAO.findAllByCategoryAliasAndStatus(categoryAlias, status);
	}

	@Override
	public BrandModel findOneByCategoryAliasAndAlias(String categoryAlias, String alias) {
		// TODO Auto-generated method stub
		return brandDAO.findOneByCategoryAliasAndAlias(categoryAlias, alias);
	}

	@Override
	public Map<String, String> delete(String id) {
		Map<String, String> map = new HashMap<String, String>();
		if(brandDAO.delete(id)) {
			map.put("success", "Xóa thành công");
		} else {
			BrandModel model = brandDAO.findOneById(id);
			map.put("danger", model.getName() + " có chứa các nhãn hàng và nhóm nhóm sản phẩm. Không thể xóa");
		}
		
		return map;
	}

	@Override
	public Map<String, String> deleteAll(String[] id) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess = true;
		String modelFail = "";
		for(String i : id ) {
			if(!brandDAO.delete(i))  {
				BrandModel model = brandDAO.findOneById(i);
				modelFail += model.getName() + ", ";
				isSuccess = false;
			}
		}
		if(isSuccess == true) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", modelFail + " có chứa các nhãn hàng và nhóm nhóm sản phẩm. Không thể xóa");
		}
		
		
		return map;
	}

}

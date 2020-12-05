package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IUserDAO;
import dao.imp.UserDAO;
import model.UserModel;
import service.IUserService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class UserService implements IUserService{
	
	private IUserDAO userDAO = UserDAO.getInstance();

	private static UserService service = null;

	public static UserService getInstance() {
		if (service == null) {
			service = new UserService();
		}
		return service;
	}

	
	@Override
	public Map<String, String> insert(UserModel model, boolean isConfirm) {
		Map<String, String> map = new HashMap<String, String>();
		String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getFullName(), 2, 30);
		
		if(checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Họ tên không hợp lệ. Không được chứa ký tự đặc biệt");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Họ tên không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
			return map;
		}
		if(checkName.equals(SystemContain.CONTAINS_DIGIT)) {
			map.put("danger", "Họ tên không hợp lệ. Không được chứa số trong tên");
			return map;
		}
		
		String checkEmail = Valid.checkEmail(model.getEmail(), 2, 100);
		if(checkEmail.equals(SystemContain.NOTEMAIL)) {
			map.put("danger", "Email không hợp lệ.");
			return map;
		}
		if(checkName.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Email quá dài");
			return map;
		}
		if(!Valid.checkPassword(model.getPassword())) {
			map.put("danger", "Password không hợp lệ. Độ dài tối thiểu 8 ký tự. Phải có chữ viết hoa, chữ số và chữ thường");
			return map;
		} else if(isConfirm == true){
			model.setPassword(Utils.maHoaMD5(model.getPassword()));
		}
		
		UserModel existEmail = findOneByEmail(model.getEmail());
		if(existEmail != null) {
			if(existEmail.getPassword().equals(Utils.maHoaMD5("123456"))) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				model.setRole("user");
				model.setStatus("active");
				model.setUpdateDate(timestamp);
				model.setUpdateBy("admin");
				if(userDAO.update(model, existEmail.getId()) == true) {
					map.put("success", "Thêm thành công.");
					return map;
				} else {
					map.put("danger", "Thêm thất bại.");
					return map;
				}
			} else {
				map.put("danger", "Email đăng ký đã tồn tại.");
				return map;
			}
		} else {
			if(isConfirm == true) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				model.setRole("user");
				model.setStatus("active");
				model.setCreateDate(timestamp);
				model.setCreateBy("admin");
				model.setUpdateDate(null);
				model.setUpdateBy("");
				Random rand = new Random();
				String id = String.valueOf(rand.nextInt(101) + 100);
	
				id += String.valueOf(new Date().getTime());
				model.setId(id);
				
				if(userDAO.insert(model) == true) {
					map.put("success", "Thêm thành công.");
					return map;
				} else {
					map.put("danger", "Thêm thất bại.");
					return map;
				}
			} else {
				map.put("confirm", "confirm");
				return map;
			}
		}
	}

	@Override
	public UserModel findOneByEmail(String email) {
		return userDAO.findOneByEmail(email);
	}

	@Override
	public UserModel findOneByEmailAndPassword(String email, String password) {
		return userDAO.findOneByEmailAndPassword(email, Utils.maHoaMD5(password));
	}


	@Override
	public UserModel findOneById(String id) {
		return userDAO.findOneById(id);
	}


	@Override
	public boolean insertUseFB(UserModel model) {
		model.setPassword(Utils.maHoaMD5(model.getPassword()));
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		model.setRole("user");
		model.setStatus("active");
		model.setCreateDate(timestamp);
		model.setCreateBy("admin");
		model.setUpdateDate(null);
		model.setUpdateBy("");
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);

		id += String.valueOf(new Date().getTime());
		model.setId(id);
		
		return userDAO.insert(model);
	}


	@Override
	public Map<String, String> update(UserModel model, String id) {
		
		return null;
	}


	@Override
	public boolean updatePassword(String email, String password) {
		String psw = Utils.maHoaMD5(password);
		return userDAO.updatePassword(email, psw);
	}


	@Override
	public List<UserModel> findAllByRoleAndStatus(String role, String status) {
		
		return userDAO.findAllByRoleAndStatus(role, status);
	}


	@Override
	public boolean updateLastLogin(Timestamp time, String email) {
		return userDAO.updateLastLogin(time, email);
	}


	@Override
	public int countByRole(String role) {
		return userDAO.countByRole(role);
	}


	@Override
	public int countAll() {
		return userDAO.countAll();
	}


	@Override
	public Map<String, String> updateStatus(String status, String id) {
		Map<String, String> map = new HashMap<>();
		if(userDAO.updateStatus(status, id)) {
			map.put("success", "Cập nhật thành công");
		} else {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại");
		}
		return map;
	}


	@Override
	public Map<String, String> deleteOne(String id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			userDAO.delete(id);
			map.put("success", "Xóa thành công");
		} catch (Exception e) {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại.");
		}
		return map;
	}


	@Override
	public Map<String, String> deleteAll(String[] ids) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess = true;
		String modelFail = "";
		for(String i : ids ) {
			if(!userDAO.delete(i))  {
				UserModel bill = userDAO.findOneById(i);
				modelFail += bill.getFullName() + ", ";
				isSuccess = false;
			}
		}
		if(isSuccess == true) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", modelFail + " Không thể xóa");
		}
		return map;
	}

}

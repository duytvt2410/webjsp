package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
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
	public Map<String, String> insert(UserModel model) {
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
		
		String checkEmail = Valid.checkNameNotContainSpecialCharacterDigit(model.getEmail(), 2, 100);
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
		} else {
			model.setPassword(Utils.maHoaMD5(model.getPassword()));
		}
		
		//model.setAlias(Utils.formatAlias(model.getName()));
		
		if(findOneByEmail(model.getEmail()) != null) {
			map.put("danger", "Email đăng ký đã tồn tại.");
			return map;
		}
		
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
		model.setId(Long.parseLong(id));
		
		if(userDAO.insert(model) == true) {
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
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

}

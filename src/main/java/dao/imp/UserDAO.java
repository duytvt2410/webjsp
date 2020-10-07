package dao.imp;

import java.util.List;

import dao.IUserDAO;
import mapper.UserMapper;
import model.UserModel;

public class UserDAO extends AbstactDAO<UserModel> implements IUserDAO {
	
private static UserDAO dao = null;
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}



//	@Override
//	public boolean insert(UserModel model) {
//		StringBuilder sql = new StringBuilder();
//		sql.append(
//				"INSERT INTO account (id, fullname, email, password, role, status, created_date, created_by, updated_date, updated_by)");
//		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//		return excute(sql.toString(), model.getId(), model.getFullName(), model.getEmail(), model.getPassword(), model.getRole(),
//				model.getStatus(), model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(),
//				model.getUpdateBy());
//	}
//
//	@Override
//	public boolean update(UserModel model) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public UserModel findOneByEmail(String email) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM account");
//		sql.append(" WHERE email = ?");
//		List<UserModel> list = query(sql.toString(), new UserMapper(), email);
//		return list.size() != 0 ? list.get(0) : null;
//	}
//
//	@Override
//	public UserModel findOneByEmailAndPassword(String email, String password) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM account");
//		sql.append(" WHERE email = ? AND password = ?");
//		List<UserModel> list = query(sql.toString(), new UserMapper(), email, password);
//		return list.size() != 0 ? list.get(0) : null;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean insert(UserModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO user (id, fullname, email, password, role, status, created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getFullName(), model.getEmail(), model.getPassword(), model.getRole(),
				model.getStatus(), model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(),
				model.getUpdateBy());
	}

	@Override
	public boolean update(UserModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserModel findOneByEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user");
		sql.append(" WHERE email = ?");
		List<UserModel> list = query(sql.toString(), new UserMapper(), email);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public UserModel findOneByEmailAndPassword(String email, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user");
		sql.append(" WHERE email = ? AND password = ?");
		List<UserModel> list = query(sql.toString(), new UserMapper(), email, password);
		return list.size() != 0 ? list.get(0) : null;
	}
}

package dao.imp;

import java.sql.Timestamp;
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
	public boolean update(UserModel model, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user");
		sql.append(" SET fullname = ?, password = ?, role = ?, status = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getFullName(), model.getPassword(), model.getRole(), model.getStatus(),
				model.getUpdateDate(), model.getUpdateBy(), id);
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



	@Override
	public UserModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user");
		sql.append(" WHERE id = ?");
		List<UserModel> list = query(sql.toString(), new UserMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}



	@Override
	public boolean updatePassword(String email, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user");
		sql.append(" SET password = ?");
		sql.append(" WHERE email = ?;");
		return excute(sql.toString(), password, email);
	}



	@Override
	public List<UserModel> findAllByRoleAndStatus(String role, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user");
		sql.append(" WHERE role = ? AND status = ?");
		return query(sql.toString(), new UserMapper(), role, status);
	}



	@Override
	public boolean updateLastLogin(Timestamp time, String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user");
		sql.append(" SET last_login = ?");
		sql.append(" WHERE email = ?;");
		return excute(sql.toString(), time, email);
	}



	@Override
	public int countByRole(String role) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM user");
		sql.append(" WHERE role = ? ");		
		return queryCount(sql.toString(), role);
	}



	@Override
	public int countAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM user");	
		return queryCount(sql.toString());
	}



	@Override
	public boolean updateStatus(String status, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user");
		sql.append(" SET status = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), status, id);
	}



	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM user");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}
}

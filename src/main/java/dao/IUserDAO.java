package dao;

import java.sql.Timestamp;
import java.util.List;

import model.UserModel;

public interface IUserDAO {
	boolean insert(UserModel model);
	boolean update(UserModel model, String id);
	
	boolean updatePassword(String email, String password);
	List<UserModel> findAllByRoleAndStatus(String role, String status);
	boolean updateLastLogin(Timestamp time, String email);
	boolean updateStatus(String status, String id);
	boolean delete(String id);
	UserModel findOneByEmail(String email);
	UserModel findOneById(String id);
	UserModel findOneByEmailAndPassword(String email, String password);
	
	int countByRole(String role);
	int countAll();
}

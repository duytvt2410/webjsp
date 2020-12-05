package service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import model.UserModel;

public interface IUserService {
	Map<String, String> insert(UserModel model, boolean isConfirm);
	Map<String, String> update(UserModel model, String id);
	Map<String, String> updateStatus(String status, String id);
	Map<String, String> deleteOne( String id);
	Map<String, String> deleteAll( String ids[]);

	boolean insertUseFB(UserModel model);
	boolean updatePassword(String email, String password);
	List<UserModel> findAllByRoleAndStatus(String role, String status);
	boolean updateLastLogin(Timestamp time, String email);
	int countByRole(String role);
	UserModel findOneByEmail(String email);
	UserModel findOneById(String id);
	UserModel findOneByEmailAndPassword(String email, String password);
	int countAll();
}

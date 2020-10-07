package dao;

import model.UserModel;

public interface IUserDAO {
	boolean insert(UserModel model);
	boolean update(UserModel model);
	
	UserModel findOneByEmail(String email);
	UserModel findOneByEmailAndPassword(String email, String password);
}

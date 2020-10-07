package service;

import java.util.Map;

import model.UserModel;

public interface IUserService {
	Map<String, String> insert(UserModel model);
	UserModel findOneByEmail(String email);
	
	UserModel findOneByEmailAndPassword(String email, String password);
}

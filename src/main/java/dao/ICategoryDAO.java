package dao;

import java.util.List;

import model.CategoryModel;

public interface ICategoryDAO {
	List<CategoryModel> findAll(); 
	List<CategoryModel> findAllByIsAccessories(String isAccessories);
	List<CategoryModel> findAllByStatus(String status);
	List<CategoryModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status); 
	CategoryModel findOneByAlias(String alias);
	CategoryModel findOneById(String id);
	boolean insert(CategoryModel model);
	boolean update(CategoryModel model, String id);
	boolean delete(String id);
}

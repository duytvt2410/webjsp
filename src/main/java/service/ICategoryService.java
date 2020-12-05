package service;

import java.util.List;
import java.util.Map;

import model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	List<CategoryModel> findAllByIsAccessories(String isAccessories);
	List<CategoryModel> findAllByStatus(String status);
	List<CategoryModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status);
	CategoryModel findOneByAlias(String alias);
	CategoryModel findOneById(String id);
	Map<String, String> insert(CategoryModel model);
	Map<String, String> update(CategoryModel model, String id);
	Map<String, String> delete(String id);
	Map<String, String> deleteAll(String[] id);
}

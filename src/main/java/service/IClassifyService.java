package service;

import java.util.List;
import java.util.Map;

import model.ClassifyModel;

public interface IClassifyService {
	List<ClassifyModel> findAll();
	List<ClassifyModel> findAllByCategoryId(String id);
	List<ClassifyModel> findAllByStatus(String status);
	List<ClassifyModel> findAllByCategoryAlias(String status);
	List<ClassifyModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status);
	ClassifyModel findOneByAlias(String alias);
	ClassifyModel findOneById(String id);
	Map<String, String> insert(ClassifyModel model);
	Map<String, String> update(ClassifyModel model, String id);
	Map<String, String> delete(String id);
	Map<String, String> deleteAll(String[] id);
}

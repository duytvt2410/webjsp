package dao;

import java.util.List;

import model.ClassifyModel;

public interface IClassifyDAO {
	List<ClassifyModel> findAll(); 
	List<ClassifyModel> findAllByCategoryId(int id);
	List<ClassifyModel> findAllByStatus(String status);
	List<ClassifyModel> findAllByCategoryAlias(String categoryAlias);
	List<ClassifyModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status);
	ClassifyModel findOneByAlias(String alias);
	ClassifyModel findOneById(Long id);
	boolean insert(ClassifyModel model);
	boolean update(ClassifyModel model, Long id);
	
}

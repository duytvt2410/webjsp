package dao;

import java.util.List;

import model.BrandModel;

public interface IBrandDAO {
	List<BrandModel> findAll(); 
	List<BrandModel> findAllByCategoryId(String id);
	List<BrandModel> findAllByStatus(String status);
	List<BrandModel> findAllByCategoryAlias(String categoryAlias);
	List<BrandModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status);
	BrandModel findOneByAlias(String alias);
	BrandModel findOneByCategoryAliasAndAlias(String categoryAlias,String alias);
	BrandModel findOneById(String id);
	boolean insert(BrandModel model);
	boolean update(BrandModel model, String id);
	boolean delete(String id);
}

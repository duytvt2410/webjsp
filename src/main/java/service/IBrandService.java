package service;

import java.util.List;
import java.util.Map;

import model.BrandModel;

public interface IBrandService {
	List<BrandModel> findAll();
	List<BrandModel> findAllByCategoryId(String id);
	List<BrandModel> findAllByStatus(String status);
	List<BrandModel> findAllByCategoryAlias(String status);
	List<BrandModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status);
	BrandModel findOneByCategoryAliasAndAlias(String categoryAlias, String alias);
	BrandModel findOneByAlias(String alias);
	BrandModel findOneById(String id);
	Map<String, String> insert(BrandModel model);
	Map<String, String> update(BrandModel model, String id);
	Map<String, String> delete(String id);
	Map<String, String> deleteAll(String[] id);
}

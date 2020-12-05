package service;

import java.util.List;
import java.util.Map;

import model.ProductModel;

public interface IProductService {
	List<ProductModel> findAll(); 
	List<ProductModel> findAllByStatus(String status);
	List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status); 
	List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status);
	List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories, String status);
	List<ProductModel> findAllRelatedProduct(String brandId, String idProduct, String status);
	List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, int limit, int start, Object... parameter);
	List<ProductModel> findAllByBrandIdAndStatus(String brandId, String status);
	List<ProductModel> findAllByKeyword(String condition, Object... keyParameter);
	List<ProductModel> findAllByCategoryAndIsNewAndStatusAndLimit(String categoryAlias, String isNew, String status, int limit);
	List<ProductModel> findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit(String is_accessories, String isNew, String status, int limit);
	int countAllCustomCondition(String condition, String categoryAlias, String status, Object... parameter);
	int countAll();
	int countAllByCategory(String categoryAlias);
	ProductModel findOneByAlias(String alias);
	ProductModel findOneById(String id);
	Map<String, String> insert(ProductModel model);
	Map<String, String> update(ProductModel model, String id);
	boolean updateQty(int qty, String id);
	boolean updateRated(double rated, String id);
	boolean updateIsNew(String isNew, String id);
	Map<String, String> delete(String id);
	Map<String, String> deleteAll(String[] id);
}

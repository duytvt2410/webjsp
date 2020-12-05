package dao;

import java.util.List;

import model.ProductModel;

public interface IProductDAO {
	List<ProductModel> findAll(); 
	List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status);  
	List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status); 
	List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories, String status);
	List<ProductModel> findAllRelatedProduct(String brandId, String idProduct, String status);
	List<ProductModel> findAllByBrandIdAndStatus(String brandId, String status);
	
	List<ProductModel> findAllByCategoryAndIsNewAndStatusAndLimit(String categoryAlias, String isNew, String status, int limit);
	List<ProductModel> findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit(String is_accessories, String isNew, String status, int limit);
	
	List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, int limit, int start, Object... idsParameter);
	
	List<ProductModel> findAllByKeyword(String condition, Object... keyParameter);
	int countAllCustomCondition(String condition, String categoryAlias, String status, Object... idsParameter);
	
	ProductModel findOneByAlias(String alias);
	ProductModel findOneById(String id);
	
	boolean insert(ProductModel model);
	boolean update(ProductModel model, String id);
	boolean updateRated(double rated, String id);
	boolean updateIsNew(String isNew, String id);
	boolean updateQty(int qty, String id);
	boolean delete(String id);
	
	
	int countAll();
	int countAllByCategory(String category);
}

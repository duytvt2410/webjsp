package dao;

import java.util.List;

import model.ProductModel;

public interface IProductDAO {
	List<ProductModel> findAll(); 
	List<ProductModel> findAllByIsAccessories(String isAccessories);
	List<ProductModel> findAllByStatus(String status);
	List<ProductModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status); 
	List<ProductModel> findAllByCategoryAndStatusAndOrderByCreateDate(String categoryAlias, String status); 
	List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status); 
	List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndOrderByCreateDate(String isAccessories, String status); 
	List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status); 
	List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories, String status);
	List<ProductModel> findAllRelatedProduct(Long brandId, Long idProduct, String status);
	List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, Object... idsParameter);
	ProductModel findOneByAlias(String alias);
	ProductModel findOneById(Long id);
	
	boolean insert(ProductModel model);
	boolean update(ProductModel model, Long id);
	
}

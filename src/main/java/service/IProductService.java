package service;

import java.util.List;
import java.util.Map;

import model.ProductModel;

public interface IProductService {
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
	List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, Object... parameter);
	
	ProductModel findOneByAlias(String alias);
	ProductModel findOneById(Long id);
	Map<String, String> insert(ProductModel model);
	Map<String, String> update(ProductModel model, Long id);
}

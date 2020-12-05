package service;

import java.util.List;
import java.util.Map;

import model.ProductClassifyModel;

public interface IProductClassifyService {
	
	Map<String, String> insert(ProductClassifyModel model);
	Map<String, String> update(ProductClassifyModel model, String id);
	boolean deleteByProductId(String id);
	

	List<ProductClassifyModel> findAllByProductId(String id);
	List<ProductClassifyModel> findAllByClassifyId(String id);
}

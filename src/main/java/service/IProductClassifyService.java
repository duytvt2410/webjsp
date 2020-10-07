package service;

import java.util.List;
import java.util.Map;

import model.ProductClassifyModel;

public interface IProductClassifyService {
	
	Map<String, String> insert(ProductClassifyModel model);
	Map<String, String> update(ProductClassifyModel model, Long id);
	boolean deleteByProductId(Long id);
	

	List<ProductClassifyModel> findAllByProductId(Long id);
}

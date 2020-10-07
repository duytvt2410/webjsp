package dao;

import java.util.List;

import model.ProductClassifyModel;

public interface IProductClassifyDAO {
	
	boolean insert(ProductClassifyModel model);
	boolean update(ProductClassifyModel model, Long id);
	boolean deleteByProductId(Long id);
	
	List<ProductClassifyModel> findAllByProductId(Long id);
	
}

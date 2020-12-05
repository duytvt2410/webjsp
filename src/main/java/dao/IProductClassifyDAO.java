package dao;

import java.util.List;

import model.ProductClassifyModel;

public interface IProductClassifyDAO {
	
	boolean insert(ProductClassifyModel model);
	boolean update(ProductClassifyModel model, String id);
	boolean deleteByProductId(String id);
	
	List<ProductClassifyModel> findAllByProductId(String id);
	List<ProductClassifyModel> findAllByClassifyId(String id);
	
}

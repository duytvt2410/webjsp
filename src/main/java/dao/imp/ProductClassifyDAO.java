package dao.imp;

import java.util.List;

import dao.IProductClassifyDAO;
import mapper.ProductClassifyMapper;
import model.ProductClassifyModel;

public class ProductClassifyDAO extends AbstactDAO<ProductClassifyModel> implements IProductClassifyDAO {

private static ProductClassifyDAO dao = null;
	
	public static ProductClassifyDAO getInstance() {
		if(dao == null) {
			dao = new ProductClassifyDAO();
		}
		return dao;
	}

	
	@Override
	public boolean insert(ProductClassifyModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO product_classify (id, alias, product_id, classify_id)");
		sql.append(" VALUES (?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getAlias(), model.getProductId(), 
				model.getClassifyId());
	}

	
	@Override
	public boolean update(ProductClassifyModel model, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE category");
		sql.append(" SET name = ?, alias = ?, status = ?, is_accessories = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getName(), model.getAlias(), model.getStatus(), model.getUpdateDate(),
				model.getUpdateBy(), id);
	}
	 

	@Override
	public List<ProductClassifyModel> findAllByProductId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product_classify.*, classify.name AS classify_name FROM product_classify ");
		sql.append(" JOIN product ON product_classify.product_id = product.id JOIN classify ON product_classify.classify_id = classify.id");
		sql.append(" WHERE product_classify.product_id = ?");
		return query(sql.toString(), new ProductClassifyMapper(), id);
	}


	@Override
	public boolean deleteByProductId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM product_classify");
		sql.append(" WHERE product_id = ?");
		return excute(sql.toString(), id);
	}


	@Override
	public List<ProductClassifyModel> findAllByClassifyId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product_classify.*, classify.name AS classify_name FROM product_classify ");
		sql.append(" JOIN product ON product_classify.product_id = product.id JOIN classify ON product_classify.classify_id = classify.id");
		sql.append(" WHERE product_classify.classify_id = ?");
		return query(sql.toString(), new ProductClassifyMapper(), id);
	}


}

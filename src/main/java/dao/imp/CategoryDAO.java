package dao.imp;

import java.util.List;

import dao.ICategoryDAO;
import mapper.CategoryMapper;
import model.CategoryModel;

public class CategoryDAO extends AbstactDAO<CategoryModel> implements ICategoryDAO {
	
private static CategoryDAO categoryDao = null;
	
	public static CategoryDAO getInstance() {
		if(categoryDao == null) {
			categoryDao = new CategoryDAO();
		}
		return categoryDao;
	}

	@Override
	public List<CategoryModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category");
		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public boolean insert(CategoryModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO category (id, name, alias, status, is_accessories, created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getName(), model.getAlias(), model.getStatus(), model.getIsAccessories(),
				model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(), model.getUpdateBy());
	}

	@Override
	public CategoryModel findOneByAlias(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category");
		sql.append(" WHERE alias = ?");
		List<CategoryModel> list = query(sql.toString(), new CategoryMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean update(CategoryModel model, Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE category");
		sql.append(" SET name = ?, alias = ?, status = ?, is_accessories = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getName(), model.getAlias(), model.getStatus(), model.getIsAccessories(),
				model.getUpdateDate(), model.getUpdateBy(), id);
	}

	@Override
	public CategoryModel findOneById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category");
		sql.append(" WHERE id = ?");
		List<CategoryModel> list = query(sql.toString(), new CategoryMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public List<CategoryModel> findAllByIsAccessories(String isAccessories) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE is_accessories = ?");
		return query(sql.toString(), new CategoryMapper(), isAccessories);
	}

	@Override
	public List<CategoryModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE is_accessories = ? AND status = ?");
		return query(sql.toString(), new CategoryMapper(), isAccessories, status);
	}

	@Override
	public List<CategoryModel> findAllByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE status = ?");
		return query(sql.toString(), new CategoryMapper(), status);
	}

	@Override
	public boolean delete(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM category");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}

}

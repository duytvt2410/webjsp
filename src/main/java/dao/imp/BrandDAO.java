package dao.imp;

import java.util.List;

import dao.IBrandDAO;
import mapper.BrandMapper;
import model.BrandModel;

public class BrandDAO extends AbstactDAO<BrandModel> implements IBrandDAO {

	private static BrandDAO brandDAO = null;
	
	public static BrandDAO getInstance() {
		if(brandDAO == null) {
			brandDAO = new BrandDAO();
		}
		return brandDAO;
	}
	
	@Override
	public List<BrandModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		return query(sql.toString(), new BrandMapper());
	}

	@Override
	public List<BrandModel> findAllByCategoryId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandModel> findAllByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE brand.status = ?");
		return query(sql.toString(), new BrandMapper(), status);
	}

	@Override
	public BrandModel findOneByAlias(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE brand.alias = ?");
		List<BrandModel> list = query(sql.toString(), new BrandMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public BrandModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE brand.id = ?");
		List<BrandModel> list = query(sql.toString(), new BrandMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean insert(BrandModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO brand (id, name, alias, status, category_id, created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getName(), model.getAlias(), model.getStatus(), model.getCategoryId(),
				model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(), model.getUpdateBy());
	}

	@Override
	public boolean update(BrandModel model, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE brand");
		sql.append(" SET name = ?, alias = ?, status = ?, category_id = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getName(), model.getAlias(), model.getStatus(), model.getCategoryId(),
				model.getUpdateDate(), model.getUpdateBy(), id);
	}

	@Override
	public List<BrandModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE category.alias = ? AND brand.status = ?");
		return query(sql.toString(), new BrandMapper(), categoryAlias, status);
	}

	@Override
	public List<BrandModel> findAllByCategoryAlias(String categoryAlias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE category.alias = ?");
		return query(sql.toString(), new BrandMapper(), categoryAlias);
	}

	@Override
	public BrandModel findOneByCategoryAliasAndAlias(String categoryAlias, String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT brand.*, category.name AS category_name, category.alias AS category_alias FROM brand");
		sql.append(" JOIN category ON brand.category_id = category.id");
		sql.append(" WHERE category.alias = ? AND brand.alias = ?");
		List<BrandModel> list = query(sql.toString(), new BrandMapper(), categoryAlias, alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM brand");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}

}

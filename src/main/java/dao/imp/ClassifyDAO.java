package dao.imp;

import java.util.List;

import dao.IClassifyDAO;
import mapper.ClassifyMapper;
import model.ClassifyModel;

public class ClassifyDAO extends AbstactDAO<ClassifyModel> implements IClassifyDAO {

	private static ClassifyDAO dao = null;
	
	public static ClassifyDAO getInstance() {
		if(dao == null) {
			dao = new ClassifyDAO();
		}
		return dao;
	}
	
	@Override
	public List<ClassifyModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		return query(sql.toString(), new ClassifyMapper());
	}

	@Override
	public List<ClassifyModel> findAllByCategoryId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassifyModel> findAllByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		sql.append(" WHERE classify.status = ?");
		return query(sql.toString(), new ClassifyMapper(), status);
	}

	@Override
	public ClassifyModel findOneByAlias(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		sql.append(" WHERE classify.alias = ?");
		List<ClassifyModel> list = query(sql.toString(), new ClassifyMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public ClassifyModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		sql.append(" WHERE classify.id = ?");
		List<ClassifyModel> list = query(sql.toString(), new ClassifyMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean insert(ClassifyModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO classify (id, name, alias, status, category_id, created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getName(), model.getAlias(), model.getStatus(), model.getCategoryId(),
				model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(), model.getUpdateBy());
	}

	@Override
	public boolean update(ClassifyModel model, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE classify");
		sql.append(" SET name = ?, alias = ?, status = ?, category_id = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getName(), model.getAlias(), model.getStatus(), model.getCategoryId(),
				model.getUpdateDate(), model.getUpdateBy(), id);
	}

	@Override
	public List<ClassifyModel> findAllByCategoryAliasAndStatus(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		sql.append(" WHERE category.alias = ? AND classify.status = ?");
		return query(sql.toString(), new ClassifyMapper(), categoryAlias, status);
	}

	@Override
	public List<ClassifyModel> findAllByCategoryAlias(String categoryAlias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classify.*, category.name AS category_name, category.alias AS category_alias FROM classify");
		sql.append(" JOIN category ON classify.category_id = category.id");
		sql.append(" WHERE category.alias = ?");
		return query(sql.toString(), new ClassifyMapper(), categoryAlias);
	}

	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM classify");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}

}

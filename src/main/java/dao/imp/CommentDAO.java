package dao.imp;

import java.util.List;

import dao.ICommentDAO;
import mapper.CommentMapper;
import model.CommentModel;

public class CommentDAO extends AbstactDAO<CommentModel> implements ICommentDAO{
	
	private static CommentDAO dao = null;

	public static CommentDAO getInstance() {
		if(dao == null) {
			dao = new CommentDAO();
		}
		return dao;
	}
	
	@Override
	public boolean insert(CommentModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO comment (id, user_id, product_id, content, star, alias, created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getUserId(), model.getProductId(), model.getContent(), model.getStar(),
				model.getAlias(), model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(),
				model.getUpdateBy());
	}

	@Override
	public boolean update(CommentModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE comment");
		sql.append(" SET content = ?, star = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), model.getContent(), model.getStar(), model.getUpdateDate(),
				model.getUpdateBy(), model.getId());
	}

	@Override
	public List<CommentModel> findAllByProductId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT comment.*, user.fullname AS user_name FROM comment JOIN user ON comment.user_id = user.id ");
		sql.append(" WHERE comment.product_id = ?");
		sql.append(" ORDER BY comment.created_date DESC");
		return query(sql.toString(), new CommentMapper(), id);
	}

	@Override
	public CommentModel findOneByAlias(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT comment.*, user.fullname AS user_name FROM comment JOIN user ON comment.user_id = user.id ");
		sql.append(" WHERE comment.alias = ?");
		List<CommentModel> list = query(sql.toString(), new CommentMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public CommentModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT comment.*, user.fullname AS user_name FROM comment JOIN user ON comment.user_id = user.id ");
		sql.append(" WHERE comment.id = ?");
		List<CommentModel> list = query(sql.toString(), new CommentMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM comment");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}

	@Override
	public int countAllByProductId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM comment");
		sql.append(" WHERE product_id = ? ");
		return queryCount(sql.toString(), id);
	}

	@Override
	public List<CommentModel> findAllByLimit(String id, int limit, int start) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT comment.*, user.fullname AS user_name FROM comment JOIN user ON comment.user_id = user.id ");
		sql.append(" WHERE comment.product_id = ?");
		sql.append(" ORDER BY comment.created_date DESC LIMIT ? OFFSET ?");		
		return query(sql.toString(), new CommentMapper(), id, limit, start);
	}

}

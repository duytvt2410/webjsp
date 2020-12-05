package dao;

import java.util.List;

import model.CommentModel;

public interface ICommentDAO {
	boolean insert(CommentModel model);
	boolean update(CommentModel model);
	boolean delete(String id);
	
	List<CommentModel> findAllByProductId(String id);
	List<CommentModel> findAllByLimit(String id, int limit, int start);
	CommentModel findOneByAlias(String alias);
	CommentModel findOneById(String id);
	
	int countAllByProductId(String id);
}

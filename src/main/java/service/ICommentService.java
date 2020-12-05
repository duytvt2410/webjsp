package service;

import java.util.List;
import java.util.Map;

import model.CommentModel;

public interface ICommentService {
	Map<String, String> insert(CommentModel model);
	Map<String, String> update(CommentModel model);
	void delete(String id);
	List<CommentModel> findAllByProductId(String id);
	List<CommentModel> findAllAndLimit(String id, int limit, int start);
	
	
	CommentModel findOneByAlias(String alias);
	CommentModel findOneById(String id);
	
	
	int countAllByProductId(String id);
}

package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.CommentModel;

public class CommentMapper implements RowMapper<CommentModel> {

	@Override
	public CommentModel mapRow(ResultSet resultSet) {
		CommentModel model = new CommentModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setAlias(resultSet.getString("alias"));
			model.setUserId(resultSet.getString("user_id"));
			model.setNameUser(resultSet.getString("user_name"));
			model.setProductId(resultSet.getString("product_id"));
			model.setStar(resultSet.getInt("star"));
			model.setContent(resultSet.getString("content"));
			model.setCreateBy(resultSet.getString("created_by"));
			model.setUpdateBy(resultSet.getString("updated_by"));
			model.setCreateDate(resultSet.getTimestamp("created_date"));
			model.setUpdateDate(resultSet.getTimestamp("updated_date"));
		} catch (SQLException e) {
			return null;
		}
		
		return model;
	}
	
}

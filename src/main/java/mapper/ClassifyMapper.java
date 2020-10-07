package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ClassifyModel;

public class ClassifyMapper implements RowMapper<ClassifyModel> {

	@Override
	public ClassifyModel mapRow(ResultSet resultSet) {
		ClassifyModel model = new ClassifyModel();
		
		try {
			model.setId(resultSet.getLong("id"));
			model.setName(resultSet.getString("name"));
			model.setAlias(resultSet.getString("alias"));
			model.setCategoryAlias(resultSet.getString("category_alias"));
			model.setCategoryName(resultSet.getString("category_name"));
			model.setStatus(resultSet.getString("status"));
			model.setCategoryId(resultSet.getLong("category_id"));
			model.setCreateBy(resultSet.getString("created_by"));
			model.setUpdateBy(resultSet.getString("updated_by"));
			model.setCreateDate(resultSet.getTimestamp("created_date"));
			model.setUpdateDate(resultSet.getTimestamp("updated_date"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

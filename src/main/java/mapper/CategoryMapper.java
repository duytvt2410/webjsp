package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		CategoryModel model = new CategoryModel();
		
		try {
			model.setId(resultSet.getLong("id"));
			model.setName(resultSet.getString("name"));
			model.setAlias(resultSet.getString("alias"));
			model.setStatus(resultSet.getString("status"));
			model.setIsAccessories(resultSet.getString("is_accessories"));
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

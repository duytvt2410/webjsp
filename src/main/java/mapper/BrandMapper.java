package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BrandModel;

public class BrandMapper implements RowMapper<BrandModel> {

	@Override
	public BrandModel mapRow(ResultSet resultSet) {
		BrandModel model = new BrandModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setName(resultSet.getString("name"));
			model.setAlias(resultSet.getString("alias"));
			model.setCategoryAlias(resultSet.getString("category_alias"));
			model.setCategoryName(resultSet.getString("category_name"));
			model.setStatus(resultSet.getString("status"));
			model.setCategoryId(resultSet.getString("category_id"));
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

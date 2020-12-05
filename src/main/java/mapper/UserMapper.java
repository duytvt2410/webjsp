package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel model = new UserModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setFullName(resultSet.getString("fullname"));
			model.setEmail(resultSet.getString("email"));
			model.setPassword(resultSet.getString("password"));
			model.setRole(resultSet.getString("role"));
			model.setStatus(resultSet.getString("status"));
			model.setCreateBy(resultSet.getString("created_by"));
			model.setUpdateBy(resultSet.getString("updated_by"));
			model.setCreateDate(resultSet.getTimestamp("created_date"));
			model.setUpdateDate(resultSet.getTimestamp("updated_date"));
			model.setLastLogin(resultSet.getTimestamp("last_login"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

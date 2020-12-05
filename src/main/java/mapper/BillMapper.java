package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BillModel;

public class BillMapper implements RowMapper<BillModel> {

	@Override
	public BillModel mapRow(ResultSet resultSet) {
		BillModel model = new BillModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setFullname(resultSet.getString("fullname"));
			model.setStatus(resultSet.getString("status"));
			model.setEmail(resultSet.getString("email"));
			model.setUserId(resultSet.getString("user_id"));
			model.setAddress(resultSet.getString("address"));
			model.setNote(resultSet.getString("note"));
			model.setPhone(resultSet.getString("phone"));
			model.setTotalPrice(resultSet.getInt("total_price"));
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

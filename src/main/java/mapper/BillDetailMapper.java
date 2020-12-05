package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BillDetailModel;

public class BillDetailMapper implements RowMapper<BillDetailModel> {

	@Override
	public BillDetailModel mapRow(ResultSet resultSet) {
		BillDetailModel model = new BillDetailModel();
		
		try {
			model.setBillId(resultSet.getString("bill_id"));
			model.setProductId(resultSet.getString("product_id"));
			model.setNameProduct(resultSet.getString("name_product"));
			model.setPrice(resultSet.getInt("price"));
			model.setQty(resultSet.getInt("qty"));
			
			model.setPromotion(resultSet.getString("promotion"));
		} catch (SQLException e) {
			return null;
		}
		
		return model;
	}

}

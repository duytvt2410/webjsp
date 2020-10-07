package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductClassifyModel;

public class ProductClassifyMapper implements RowMapper<ProductClassifyModel> {

	@Override
	public ProductClassifyModel mapRow(ResultSet resultSet) {
		ProductClassifyModel model = new ProductClassifyModel();
		
		try {
			model.setId(resultSet.getLong("id"));
			model.setAlias(resultSet.getString("alias"));
			model.setProductId(resultSet.getLong("product_id"));
			model.setClassifyId(resultSet.getLong("classify_id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

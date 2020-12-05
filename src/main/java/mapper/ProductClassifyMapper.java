package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductClassifyModel;

public class ProductClassifyMapper implements RowMapper<ProductClassifyModel> {

	@Override
	public ProductClassifyModel mapRow(ResultSet resultSet) {
		ProductClassifyModel model = new ProductClassifyModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setAlias(resultSet.getString("alias"));
			model.setProductId(resultSet.getString("product_id"));
			model.setClassifyId(resultSet.getString("classify_id"));
			model.setClassifyName(resultSet.getString("classify_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

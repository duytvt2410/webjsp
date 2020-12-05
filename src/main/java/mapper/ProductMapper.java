package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel> {

	@Override
	public ProductModel mapRow(ResultSet resultSet) {
		ProductModel model = new ProductModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setName(resultSet.getString("name"));
			model.setAlias(resultSet.getString("alias"));
			try {
				model.setBrandAlias(resultSet.getString("brand_alias"));
				model.setBrandName(resultSet.getString("brand_name"));
				model.setCategoryAlias(resultSet.getString("category_alias"));
				model.setCategoryName(resultSet.getString("category_name"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			model.setStatus(resultSet.getString("status"));
			model.setPromotionInformation(resultSet.getString("promotion_information"));
			model.setDescription(resultSet.getString("description"));
			model.setSpecifications(resultSet.getString("specifications"));
			model.setBrandId(resultSet.getString("brand_id"));
			model.setPrice(resultSet.getInt("price"));
			model.setPricePromotional(resultSet.getInt("price_promotional"));
			model.setQty(resultSet.getInt("qty"));
			model.setCreateBy(resultSet.getString("created_by"));
			model.setUpdateBy(resultSet.getString("updated_by"));
			model.setCreateDate(resultSet.getTimestamp("created_date"));
			model.setUpdateDate(resultSet.getTimestamp("updated_date"));
			try {
				model.setRated(resultSet.getDouble("rated"));
			} catch (Exception e) {
				model.setRated(1);	
			}
			try {
				model.setIsNew(resultSet.getString("is_new"));
			} catch (Exception e) {
				model.setIsNew("");
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return model;
	}

}

package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductModel;
import service.InterfaceImagesService;
import service.imp.ImagesService;

public class ProductMapper implements RowMapper<ProductModel> {
	private InterfaceImagesService imagesService = new ImagesService();

	@Override
	public ProductModel mapRow(ResultSet resultSet) {
		ProductModel model = new ProductModel();
		
		try {
			model.setId(resultSet.getLong("id"));
			model.setName(resultSet.getString("name"));
			model.setAlias(resultSet.getString("alias"));
			model.setBrandAlias(resultSet.getString("brand_alias"));
			model.setBrandName(resultSet.getString("brand_name"));
			model.setCategoryAlias(resultSet.getString("category_alias"));
			model.setStatus(resultSet.getString("status"));
			model.setPromotionInformation(resultSet.getString("promotion_information"));
			model.setDescription(resultSet.getString("description"));
			model.setSpecifications(resultSet.getString("specifications"));
			model.setBrandId(resultSet.getLong("brand_id"));
			model.setPrice(resultSet.getInt("price"));
			model.setPricePromotional(resultSet.getInt("price_promotional"));
			model.setQty(resultSet.getInt("qty"));
			model.setCreateBy(resultSet.getString("created_by"));
			model.setUpdateBy(resultSet.getString("updated_by"));
			model.setCreateDate(resultSet.getTimestamp("created_date"));
			model.setUpdateDate(resultSet.getTimestamp("updated_date"));
		} catch (SQLException e) {
			return null;
		}
		model.setListImage(imagesService.findAllByProductId(model.getId()));
		return model;
	}

}

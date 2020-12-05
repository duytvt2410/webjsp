package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ImagesModel;

public class ImagesMapper implements RowMapper<ImagesModel> {

	@Override
	public ImagesModel mapRow(ResultSet resultSet) {
		ImagesModel model = new ImagesModel();
		
		try {
			model.setId(resultSet.getString("id"));
			model.setType(resultSet.getString("type"));
			model.setName(resultSet.getString("name"));
			model.setProduct_Id(resultSet.getString("product_id"));
			model.setPhoto(resultSet.getString("photo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

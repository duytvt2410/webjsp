package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ImagesModel;

public class ImagesMapper implements RowMapper<ImagesModel> {

	@Override
	public ImagesModel mapRow(ResultSet resultSet) {
		ImagesModel model = new ImagesModel();
		
		try {
			model.setId(resultSet.getLong("id"));
			model.setType(resultSet.getString("type"));
			model.setProduct_Id(resultSet.getLong("product_id"));
			model.setInputImage(resultSet.getBinaryStream("photo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return model;
	}

}

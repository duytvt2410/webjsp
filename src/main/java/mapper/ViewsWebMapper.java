package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ViewsWebModel;

public class ViewsWebMapper implements RowMapper<ViewsWebModel>{

	@Override
	public ViewsWebModel mapRow(ResultSet resultSet) {
		ViewsWebModel model = new ViewsWebModel();
		
		try {
			try {
				model.setId(resultSet.getLong("id"));
				model.setDate(resultSet.getTimestamp("date"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			try {
				model.setViews(resultSet.getLong("views"));
			} catch (Exception e) {
				int view = resultSet.getInt("views");
				model.setViews((long) view);
			}
			try {
				model.setDay(resultSet.getInt("day"));
				model.setMonth(resultSet.getInt("month"));
				model.setYear(resultSet.getInt("year"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return model;
	}

}

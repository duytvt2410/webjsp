package dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.IGenericDAO;
import mapper.RowMapper;

public class AbstactDAO<T> implements IGenericDAO<T> {
	
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webjsp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			conn = getConnection();
			preparedStatement = conn.prepareStatement(sql);
			setParameter(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}

			return results;
		} catch (SQLException e) {
			return null;
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();

			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public boolean excute(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sql);
			setParameter(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				return false;
			}
			return false;
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();

			} catch (SQLException e) {
				return false;
			}
		}
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				} else if(parameter instanceof Long []) {
					Long sss[] = (Long []) parameter;
					for(int j = 0; j < sss.length; j++) {
						statement.setLong(index, sss[j]);
						index++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

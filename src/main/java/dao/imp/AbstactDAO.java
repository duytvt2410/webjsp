package dao.imp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.IGenericDAO;
import mapper.RowMapper;

public class AbstactDAO<T> implements IGenericDAO<T> {
	
	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			String username = "xohiyrwycgepgl";
			String password = "bb64e9c291f3fefbe15b13990e77312f22049ed37ee261087a3ad4b8401dff71";
			String dbUrl = "jdbc:postgresql://"+"ec2-107-22-241-205.compute-1.amazonaws.com"+"/deqli4qc01qmfn";

			return DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	
//	public static Connection getConnection() {
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			return DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/webjsp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//					"root", "");
//		} catch (SQLException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

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
				} else if (parameter == null) {
					statement.setNull(index, Types.NULL);
				} else if(parameter instanceof InputStream) {
					statement.setBlob(index, (InputStream) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

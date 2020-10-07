package dao;

import java.util.List;

import mapper.RowMapper;

public interface IGenericDAO<T> {
	List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	boolean excute(String sql, Object... parameters);
}

package dao.imp;

import java.util.List;

import dao.IViewsWebDAO;
import mapper.ViewsWebMapper;
import model.ViewsWebModel;

public class ViewsWebDAO extends AbstactDAO<ViewsWebModel> implements IViewsWebDAO {

	private static ViewsWebDAO dao = null;

	public static ViewsWebDAO getInstance() {
		if (dao == null) {
			dao = new ViewsWebDAO();
		}
		return dao;
	}

	@Override
	public List<ViewsWebModel> findAllByYear(int year) {
		String sql = "SELECT SUM(views) AS views, DAY(date) AS day, MONTH(date) AS month, YEAR(date) AS year FROM `views_web` WHERE YEAR(date) = ? GROUP BY month";
		return query(sql, new ViewsWebMapper(), year);
	}

	@Override
	public boolean insert(ViewsWebModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO views_web (views, date) VALUES (?, ?)");
		return excute(sql.toString(), model.getViews(), model.getDate());

	}

	@Override
	public ViewsWebModel findOneByDayAndMonthAndYear(String date) {
		String sql = "SELECT * FROM views_web WHERE date BETWEEN ? AND ?";
		List<ViewsWebModel> list = query(sql.toString(), new ViewsWebMapper(), date + " 00:00:00", date + " 23:59:59");
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean update(Long view, Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE views_web");
		sql.append(" SET views = ?");
		sql.append(" WHERE id = ?");
		return excute(sql.toString(), view, id);
	}

	@Override
	public ViewsWebModel findAllView() {
		String sql = "SELECT SUM(views) AS views FROM views_web";
		List<ViewsWebModel> list = query(sql, new ViewsWebMapper());
		return list.size() != 0 ? list.get(0) : null;
	}

}

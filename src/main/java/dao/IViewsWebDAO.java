package dao;

import java.util.List;

import model.ViewsWebModel;

public interface IViewsWebDAO {
	List<ViewsWebModel> findAllByYear(int year);
	boolean insert(ViewsWebModel model);
	boolean update(Long view, Long id);
	ViewsWebModel findOneByDayAndMonthAndYear(String date);	
	ViewsWebModel findAllView();
}

package service;

import java.util.List;

import model.ViewsWebModel;

public interface IViewsWebService {
	List<ViewsWebModel> findAllByYear(int year);
	boolean insert(ViewsWebModel model);
	boolean update(Long view, Long id);
	ViewsWebModel findOneByDayAndMonthAndYear(String date);
	ViewsWebModel findAllView();
}

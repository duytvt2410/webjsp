package service.imp;

import java.util.ArrayList;
import java.util.List;

import dao.IViewsWebDAO;
import dao.imp.ViewsWebDAO;
import model.ViewsWebModel;
import service.IViewsWebService;

public class ViewsWebService implements IViewsWebService{
	
private IViewsWebDAO viewDAO = ViewsWebDAO.getInstance();
	
	private static ViewsWebService service = null;

	public static ViewsWebService getInstance() {
		if (service == null) {
			service = new ViewsWebService();
		}
		return service;
	}

	@Override
	public List<ViewsWebModel> findAllByYear(int year) {
		List<ViewsWebModel> dataViews = viewDAO.findAllByYear(year);
		List<ViewsWebModel> allMonthView = new ArrayList<ViewsWebModel>();
		for(int i = 0; i < 12; i++) {
			ViewsWebModel model = new ViewsWebModel();
			model.setViews(0L);
			model.setMonth(i + 1);
			model.setYear(year);
			allMonthView.add(model);
		}
		for(ViewsWebModel view : dataViews) {
			allMonthView.get(view.getMonth() - 1).setViews(view.getViews());
			allMonthView.get(view.getMonth() - 1).setDay(view.getDay());
			allMonthView.get(view.getMonth() - 1).setMonth(view.getMonth());
			allMonthView.get(view.getMonth() - 1).setYear(view.getYear());
		}
		return allMonthView;
	}

	@Override
	public boolean insert(ViewsWebModel model) {
		
		return viewDAO.insert(model);
	}	

	@Override
	public ViewsWebModel findOneByDayAndMonthAndYear(String date) {
		
		return viewDAO.findOneByDayAndMonthAndYear(date);
	}

	@Override
	public boolean update(Long view, Long id) {
		return viewDAO.update(view, id);
	}

	@Override
	public ViewsWebModel findAllView() {
		return viewDAO.findAllView();
	}
}

package service.imp;

import java.util.List;

import dao.IBillDetailDAO;
import dao.imp.BillDetailDAO;
import model.BillDetailModel;
import service.IBillDetailServicce;

public class BillDetailService implements IBillDetailServicce{
	private IBillDetailDAO billDetailDAO = BillDetailDAO.getInstance();
	
	private static BillDetailService service = null;

	public static BillDetailService getInstance() {
		if (service == null) {
			service = new BillDetailService();
		}
		return service;
	}
	
	@Override
	public List<BillDetailModel> findAllByBillId(String id) {
		return billDetailDAO.findAllByBillId(id);
	}

	@Override
	public boolean insert(BillDetailModel model) {
		return billDetailDAO.insert(model);
	}

	@Override
	public boolean update(BillDetailModel model, String id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delete(String id) {
		return billDetailDAO.delete(id);
	}

}

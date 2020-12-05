package service;

import java.util.List;

import model.BillDetailModel;

public interface IBillDetailServicce {
	List<BillDetailModel> findAllByBillId(String id);
	boolean insert(BillDetailModel model);
	boolean update(BillDetailModel model, String id);
	boolean delete(String id);
}

package service;

import java.util.List;
import java.util.Map;

import model.BillModel;

public interface IBillServicce {
	List<BillModel> findAll();
	List<BillModel> findAllByStatus(String status); 
	List<BillModel> findAllByUserId(String alias);
	List<BillModel> findAllByCreateDate(String date);
	BillModel findOneById(String id);
	Map<String, String> insert(BillModel model, boolean confirm);
	Map<String, String> update(BillModel model, String id);
	Map<String, String> delete(String id);
	Map<String, String> deleteAll(String id[]);
	Map<String, String> updateStatus(String status, String id);
	int countByStatus(String status);
	int countAll();
}

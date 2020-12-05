package dao;

import java.util.List;

import model.BillModel;

public interface IBillDAO {
	List<BillModel> findAll();
	List<BillModel> findAllByStatus(String status); 
	List<BillModel> findAllByUserId(String id);
	List<BillModel> findAllByCreateDate(String date);
	
	BillModel findOneById(String id);
	boolean insert(BillModel model);
	boolean update(BillModel model, String id);
	boolean delete(String id);
	boolean updateStatus(String status, String id);
	int countByStatus(String status);
	int countAll();
}

package dao.imp;

import java.util.List;

import dao.IBillDetailDAO;
import mapper.BillDetailMapper;
import model.BillDetailModel;

public class BillDetailDAO extends AbstactDAO<BillDetailModel> implements IBillDetailDAO {

	private static BillDetailDAO dao = null;
	
	public static BillDetailDAO getInstance() {
		if(dao == null) {
			dao = new BillDetailDAO();
		}
		return dao;
	}
	
	@Override
	public boolean insert(BillDetailModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO bill_detail ");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getBillId(), model.getProductId(), model.getNameProduct(), model.getPrice(),
				model.getQty(), model.getPromotion());
	}

	@Override
	public boolean update(BillDetailModel model, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM bill_detail");
		sql.append(" WHERE bill_id = ?;");
		return excute(sql.toString(), id);
	}

	@Override
	public List<BillDetailModel> findAllByBillId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill_detail ");
		sql.append(" WHERE bill_id = ?");
		return query(sql.toString(), new BillDetailMapper(), id);
	}

}

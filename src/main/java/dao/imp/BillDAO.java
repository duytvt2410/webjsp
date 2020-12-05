package dao.imp;

import java.util.List;

import dao.IBillDAO;
import mapper.BillMapper;
import model.BillModel;

public class BillDAO extends AbstactDAO<BillModel> implements IBillDAO {
	private static BillDAO dao = null;
	
	public static BillDAO getInstance() {
		if(dao == null) {
			dao = new BillDAO();
		}
		return dao;
	}
	
	@Override
	public List<BillModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillModel> findAllByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill ");
		sql.append(" WHERE status = ?");
		return query(sql.toString(), new BillMapper(), status);
	}

	@Override
	public List<BillModel> findAllByUserId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill ");
		sql.append(" WHERE user_id = ?");
		return query(sql.toString(), new BillMapper(), id);
	}

	@Override
	public BillModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill ");
		sql.append(" WHERE id = ?");
		List<BillModel> list = query(sql.toString(), new BillMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean insert(BillModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO bill ");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getUserId(), model.getFullname(), model.getEmail(),
				model.getPhone(), model.getNote(), model.getAddress(),model.getTotalPrice(), model.getStatus(), model.getCreateDate(),
				model.getCreateBy(), model.getUpdateDate(), model.getUpdateBy());
	}

	@Override
	public boolean update(BillModel model, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM bill");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}

	@Override
	public List<BillModel> findAllByCreateDate(String date) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM bill ");
		sql.append(" WHERE created_date BETWEEN ? AND ?");
		return query(sql.toString(), new BillMapper(), date + " 00:00:00", date + " 23:59:59");
	}

	@Override
	public boolean updateStatus(String status, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE bill");
		sql.append(" SET status = ?");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), status, id);
	}

	@Override
	public int countByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM bill");
		sql.append(" WHERE status = ? ");		
		return queryCount(sql.toString(), status);
	}

	@Override
	public int countAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM bill");		
		return queryCount(sql.toString());
	}

}

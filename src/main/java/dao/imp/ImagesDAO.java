package dao.imp;

import java.util.List;

import dao.InterfaceImagesDAO;
import mapper.ImagesMapper;
import model.ImagesModel;

public class ImagesDAO extends AbstactDAO<ImagesModel> implements InterfaceImagesDAO{
	
private static ImagesDAO dao = null;
	
	public static ImagesDAO getInstance() {
		if(dao == null) {
			dao = new ImagesDAO();
		}
		return dao;
	}

	@Override
	public boolean insert(ImagesModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO images (id, product_id, type, url)");
		sql.append(" VALUES (?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getProduct_Id(), model.getType(), model.getUrl() );
	}

	@Override
	public List<ImagesModel> findAllByProductId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM images");
		sql.append(" WHERE product_id = ?");
		return query(sql.toString(), new ImagesMapper(), id);
	}

	@Override
	public boolean deleteByProductIdAndType(Long id, String type) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM images");
		sql.append(" WHERE product_id = ? AND type = ?");
		return excute(sql.toString(), id, type);
	}
	
}

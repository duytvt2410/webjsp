package dao;

import java.util.List;

import model.ImagesModel;

public interface InterfaceImagesDAO {
	boolean insert(ImagesModel model);
	List<ImagesModel> findAll();
	List<ImagesModel> findAllByProductId(String id);
	List<ImagesModel> findAllByProductIdAndType(String id, String type);
	boolean deleteByProductIdAndType(String id, String type);
}

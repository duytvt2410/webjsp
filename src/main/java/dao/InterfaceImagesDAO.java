package dao;

import java.util.List;

import model.ImagesModel;

public interface InterfaceImagesDAO {
	boolean insert(ImagesModel model);
	
	List<ImagesModel> findAllByProductId(Long id);
	boolean deleteByProductIdAndType(Long id, String type);
	
	
}

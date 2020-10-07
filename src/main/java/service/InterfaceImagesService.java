package service;

import java.util.List;

import model.ImagesModel;

public interface InterfaceImagesService {
	boolean insert(ImagesModel model);
	
	List<ImagesModel> findAllByProductId(Long id);
	
	boolean deleteByProductIdAndType(Long id, String type);
}

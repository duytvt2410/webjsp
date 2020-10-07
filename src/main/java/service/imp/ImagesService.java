package service.imp;

import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.InterfaceImagesDAO;
import dao.imp.ImagesDAO;
import model.ImagesModel;
import service.InterfaceImagesService;

public class ImagesService implements InterfaceImagesService{
	
	private InterfaceImagesDAO imageDAO = ImagesDAO.getInstance();

	private static ImagesService service = null;

	public static ImagesService getInstance() {
		if (service == null) {
			service = new ImagesService();
		}
		return service;
	}

	
	@Override
	public boolean insert(ImagesModel model) {
		
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);
		
		id += String.valueOf(new Date().getTime());
		model.setId(Long.parseLong(id));
		
		return imageDAO.insert(model);
	}

	@Override
	public List<ImagesModel> findAllByProductId(Long id) {
		return imageDAO.findAllByProductId(id);
	}

	@Override
	public boolean deleteByProductIdAndType(Long id, String type) {
		return imageDAO.deleteByProductIdAndType(id, type);
	}

}

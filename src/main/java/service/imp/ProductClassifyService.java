package service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IProductClassifyDAO;
import dao.imp.ProductClassifyDAO;
import model.ProductClassifyModel;
import service.IProductClassifyService;

public class ProductClassifyService implements IProductClassifyService {

	private IProductClassifyDAO productClassifyDAO = ProductClassifyDAO.getInstance();


	private static ProductClassifyService service = null;

	public static ProductClassifyService getInstance() {
		if (service == null) {
			service = new ProductClassifyService();
		}
		return service;
	}

	
	
	@Override
	public Map<String, String> insert(ProductClassifyModel model) {
		Map<String, String> map = new HashMap<String, String>();
		
		
		model.setAlias(model.getClassifyId() +"-" + model.getProductAlias());
		
		
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);
		
		id += String.valueOf(new Date().getTime());
		model.setId(id);

		if (productClassifyDAO.insert(model) == true) {
			
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public Map<String, String> update(ProductClassifyModel model, String id) {
		return null;
	}

	@Override
	public List<ProductClassifyModel> findAllByProductId(String id) {
		return productClassifyDAO.findAllByProductId(id);
	}

	@Override
	public boolean deleteByProductId(String id) {
		return productClassifyDAO.deleteByProductId(id);
	}



	@Override
	public List<ProductClassifyModel> findAllByClassifyId(String id) {
		return productClassifyDAO.findAllByClassifyId(id);
	}

}

package service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IProductClassifyDAO;
import dao.imp.ProductClassifyDAO;
import model.ClassifyModel;
import model.ProductClassifyModel;
import service.IClassifyService;
import service.IProductClassifyService;

public class ProductClassifyService implements IProductClassifyService {

	private IProductClassifyDAO productClassifyDAO = ProductClassifyDAO.getInstance();

	private IClassifyService classifyService = ClassifyService.getInstance();

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
		
		ClassifyModel classify = classifyService.findOneById(model.getClassifyId());
		
		model.setAlias(classify.getAlias() +"-" + model.getProductAlias());
		
		
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);
		
		id += String.valueOf(new Date().getTime());
		model.setId(Long.parseLong(id));

		if (productClassifyDAO.insert(model) == true) {
			
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Thêm thất bại.");
			return map;
		}
	}

	@Override
	public Map<String, String> update(ProductClassifyModel model, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductClassifyModel> findAllByProductId(Long id) {
		return productClassifyDAO.findAllByProductId(id);
	}

	@Override
	public boolean deleteByProductId(Long id) {
		return productClassifyDAO.deleteByProductId(id);
	}

}

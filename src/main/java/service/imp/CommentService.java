package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.ICommentDAO;
import dao.imp.CommentDAO;
import model.CommentModel;
import model.ProductModel;
import service.ICommentService;
import service.IProductService;
import utils.SystemContain;
import utils.Utils;
import utils.Valid;

public class CommentService implements ICommentService{
	
	private ICommentDAO commentDAO = CommentDAO.getInstance();
	private IProductService productService = ProductService.getInstance();

	private static CommentService service = null;

	public static CommentService getInstance() {
		if (service == null) {
			service = new CommentService();
		}
		return service;
	}

	
	@Override
	public Map<String, String> insert(CommentModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkComment = Valid.checkNameNotContainSpecialCharacterDigit(model.getContent(), 2, 300);
		
		if(checkComment.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Bình luận có ký tự đặc biệt");
			return map;
		}
	
		if(checkComment.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Bình luận quá dài");
			return map;
		}

		
		model.setAlias(Utils.formatAlias(model.getProductId() + model.getUserId()));
		
		if(findOneByAlias(model.getAlias()) != null) {
			map.put("danger", "Bạn đã bình luận cho sản phẩm này rồi.");
			return map;
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		model.setCreateDate(timestamp);
		
		model.setUpdateDate(null);
		model.setUpdateBy("");
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt(101) + 100);

		id += String.valueOf(new Date().getTime());
		model.setId(id);
		
		if(commentDAO.insert(model) == true) {
			ProductModel product = productService.findOneById(model.getProductId());
			product.getListComment();
			double avg = (product.getStar5() * 5 + product.getStar4() * 4 + product.getStar3() * 3 + product.getStar2() * 2 + product.getStar1() * 1) / (product.getTotalStar());
			productService.updateRated(Math.floor(avg * 10) / 10, product.getId());
			map.put("success", "Thêm thành công.");
			return map;
		} else {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại");
			return map;
		}
	}



	@Override
	public CommentModel findOneByAlias(String alias) {
		return commentDAO.findOneByAlias(alias);
	}


	@Override
	public List<CommentModel> findAllByProductId(String id) {
		return commentDAO.findAllByProductId(id);
	}


	@Override
	public CommentModel findOneById(String id) {
		return commentDAO.findOneById(id);
	}


	@Override
	public Map<String, String> update(CommentModel model) {
		Map<String, String> map = new HashMap<String, String>();
		String checkComment = Valid.checkNameNotContainSpecialCharacterDigit(model.getContent(), 2, 300);
		
		if(checkComment.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
			map.put("danger", "Bình luận có ký tự đặc biệt");
			return map;
		}
	
		if(checkComment.equals(SystemContain.OVER_SIZE)) {
			map.put("danger", "Bình luận quá dài");
			return map;
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
		model.setUpdateDate(timestamp);	
		
		if(commentDAO.update(model) == true) {
			ProductModel product = productService.findOneById(model.getProductId());
			product.getListComment();
			double avg = (product.getStar5() * 5 + product.getStar4() * 4 + product.getStar3() * 3 + product.getStar2() * 2 + product.getStar1() * 1) / (product.getTotalStar());
			productService.updateRated(Math.floor(avg * 10) / 10, product.getId());
			map.put("success", "Sửa thành công.");
			return map;
		} else {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại.");
			return map;
		}
	}


	@Override
	public void delete(String id) {
		commentDAO.delete(id);
	}


	@Override
	public int countAllByProductId(String id) {
		return commentDAO.countAllByProductId(id);
	}


	@Override
	public List<CommentModel> findAllAndLimit(String id, int limit, int start) {
		return commentDAO.findAllByLimit(id, limit, start);
	}

}

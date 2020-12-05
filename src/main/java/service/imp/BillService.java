package service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.IBillDAO;
import dao.imp.BillDAO;
import model.BillDetailModel;
import model.BillModel;
import model.ProductModel;
import service.IBillDetailServicce;
import service.IBillServicce;
import service.IProductService;
import utils.SystemContain;
import utils.Valid;

public class BillService implements IBillServicce {
	private IBillDAO billDAO = BillDAO.getInstance();
	private IBillDetailServicce billDetailService = BillDetailService.getInstance();
	private IProductService productService = ProductService.getInstance();

	private static BillService service = null;

	public static BillService getInstance() {
		if (service == null) {
			service = new BillService();
		}
		return service;
	}

	@Override
	public List<BillModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillModel> findAllByStatus(String status) {
		return billDAO.findAllByStatus(status);
	}

	@Override
	public List<BillModel> findAllByUserId(String id) {
		return billDAO.findAllByUserId(id);
	}

	@Override
	public BillModel findOneById(String id) {
		return billDAO.findOneById(id);
	}

	@Override
	public Map<String, String> insert(BillModel model, boolean confirm) {
		if (confirm == false) {
			Map<String, String> map = new HashMap<String, String>();
			String checkName = Valid.checkNameNotContainSpecialCharacterDigit(model.getFullname(), 2, 30);
			if (checkName.equals(SystemContain.EMPTY)) {
				map.put("danger", "Họ tên không được bỏ trống");
				return map;
			}
			if (checkName.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
				map.put("danger", "Họ tên không hợp lệ. Không được chứa ký tự đặc biệt");
				return map;
			}
			if (checkName.equals(SystemContain.OVER_SIZE)) {
				map.put("danger", "Họ tên không hợp lệ. Độ dài tên chỉ được từ 2 đến 30 ký tự");
				return map;
			}
			if (checkName.equals(SystemContain.CONTAINS_DIGIT)) {
				map.put("danger", "Họ tên không hợp lệ. Không được chứa số trong tên");
				return map;
			}

			String checkEmail = Valid.checkEmail(model.getEmail(), 2, 100);
			if (checkEmail.equals(SystemContain.EMPTY)) {
				map.put("danger", "Email không bỏ trống.");
				return map;
			}
			if (checkEmail.equals(SystemContain.NOTEMAIL)) {
				map.put("danger", "Email không hợp lệ.");
				return map;
			}
			if (checkName.equals(SystemContain.OVER_SIZE)) {
				map.put("danger", "Email quá dài");
				return map;
			}

			String checkPhone = Valid.checkPhone(model.getPhone(), 2, 10);
			if (checkPhone.equals(SystemContain.EMPTY)) {
				map.put("danger", "Số điện thoại không được bỏ trống.");
				return map;
			}
			if (checkPhone.equals(SystemContain.NOTPHONE)) {
				map.put("danger", "Số điện thoại không hợp lệ.");
				return map;
			}
			if (checkPhone.equals(SystemContain.OVER_SIZE)) {
				map.put("danger", "Số điện thoại quá dài");
				return map;
			}
			if (model.getNote() == null)
				model.setNote("");
			String checkNote = Valid.checkNameNotContainSpecial(model.getNote(), 0, 200);

			if (checkNote.equals(SystemContain.OVER_SIZE)) {
				map.put("danger", "Ghi chú quá dài");
				return map;
			}

			if (checkNote.equals(SystemContain.CONTAINS_SPECIAL_CHARACTER)) {
				map.put("danger", "Ghi chú không được chứa ký tự đặc biệt");
				return map;
			}

			if (model.getListBillDetail().size() == 0) {
				map.put("danger", "Không có sản phẩm nào để thanh toán");
				return map;
			}

			for (BillDetailModel bill : model.getListBillDetail()) {
				ProductModel oldProduct = productService.findOneById(bill.getProductId());
				if (oldProduct != null) {
					if (bill.getQty() < 0 || bill.getQty() > 3) {
						map.put("danger", "Số lượng mua " + oldProduct.getName() + " không hợp lệ");
						return map;
					}

					int oldQty = oldProduct.getQty();
					int newQty = oldQty - bill.getQty();
					if (newQty < 0) {
						map.put("danger", oldProduct.getName() + " Khổng đủ hàng trong kho.");
						return map;
					}

				}
			}

			Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 28800000L);

			
			List<BillModel> listBillCreateByNowDate = findAllByCreateDate(timestamp.toString().split(" ")[0]);
			if (listBillCreateByNowDate != null ? listBillCreateByNowDate.size() < 3
					: listBillCreateByNowDate != null) {
				map.put("codeConfirm", "Nhập mã xác nhận");
				return map;
			} else {
				map.put("danger", "Bạn đã hết lượt đặt hàng trong ngày. Hãy quay lại vào ngày mai");
				return map;
			}
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			model.setStatus("confirm");
			model.setCreateDate(timestamp);
			model.setUpdateDate(null);
			model.setUpdateBy("");
			Random rand = new Random();
			String id = String.valueOf(rand.nextInt(101) + 100);

			id += String.valueOf(new Date().getTime());
			model.setId(id);
			Map<String, String> map = new HashMap<String, String>();
			if (billDAO.insert(model) == true) {
				
				for (BillDetailModel bill : model.getListBillDetail()) {
					bill.setBillId(model.getId());
					ProductModel oldProduct = productService.findOneById(bill.getProductId());
					if (oldProduct != null) {
						int oldQty = oldProduct.getQty();
						int newQty = oldQty - bill.getQty();
						productService.updateQty(newQty, oldProduct.getId());
						billDetailService.insert(bill);
					}
				}
				map.put("success", "Đặt hàng thành công.");
				return map;
			} else {
				map.put("danger", "Đặt hàng thất bại.");
				return map;
			}
		}
	}

	@Override
	public Map<String, String> update(BillModel model, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> delete(String id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			billDetailService.delete(id);
			billDAO.delete(id);
			map.put("success", "Xóa thành công");
		} catch (Exception e) {
			map.put("danger", "Có lỗi xảy ra. Vui lòng thử lại.");
		}
		return map;
	}

	@Override
	public List<BillModel> findAllByCreateDate(String date) {
		return billDAO.findAllByCreateDate(date);
	}

	@Override
	public Map<String, String> updateStatus(String status, String id) {
		Map<String, String> map = new HashMap<String, String>();
		if(status.equals("cancel")) {
			BillModel oldModel = findOneById(id);
			if(oldModel.getStatus().equals("inprocess")) {
				map.put("danger", "Kiện hàng đang được giao. Bạn không thể hủy");
			} else if(oldModel.getStatus().equals("done")) {
				map.put("danger", "Kiện hàng đã được giao. Bạn không thể hủy");
			} else {
				if(billDAO.updateStatus(status, id)) {
					map.put("success", "Hủy thành công");
					List<BillDetailModel> billDetail = billDetailService.findAllByBillId(id);
					for(BillDetailModel b : billDetail) {
						ProductModel pro = productService.findOneById(b.getProductId());
						int qty = pro.getQty() + b.getQty();
						productService.updateQty(qty, pro.getId());
					}
				} else {
					map.put("danger", "Vui lòng thử lại");
				}
			}
		} else {
			if(billDAO.updateStatus(status, id)) {
				map.put("success", "Cập nhật trạng thái thành công");
			} else {
				map.put("danger", "Vui lòng thử lại");
			}
		}
		
		return map;
	}
	
	@Override
	public Map<String, String> deleteAll(String[] id) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess = true;
		String modelFail = "";
		for(String i : id ) {
			if(!billDAO.delete(i))  {
				BillModel bill = billDAO.findOneById(i);
				modelFail += bill.getName() + ", ";
				isSuccess = false;
			}
		}
		if(isSuccess == true) {
			map.put("success", "Xóa thành công");
		} else {
			map.put("danger", modelFail + " Không thể xóa");
		}
		return map;
	}

	@Override
	public int countByStatus(String status) {
		
		return billDAO.countByStatus(status);
	}

	@Override
	public int countAll() {
		return billDAO.countAll();
	}
}

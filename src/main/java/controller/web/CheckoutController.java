package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillDetailModel;
import model.BillModel;
import model.Cart;
import model.ProductModel;
import model.UserModel;
import service.IBillServicce;
import service.imp.BillService;
import utils.SendEmail;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/thanhtoan", "/donhang", "/xacnhandon","/guilaima", "/huydonhang"})
public class CheckoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBillServicce billService = BillService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		
		Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
		BillModel model = new BillModel();
		model.setUserId(user.getId());
		model.setFullname(fullname);
		model.setEmail(email);
		model.setAddress(address);
		model.setPhone(phone);
		model.setNote(note);
		model.setTotalPrice(cart.total());
		
	
		List<BillDetailModel> listBillDetail = new ArrayList<BillDetailModel>();
		if(cart != null) {
		for(ProductModel product : cart.list()) {
			BillDetailModel bill = new BillDetailModel();
			bill.setNameProduct(product.getName());
			bill.setPrice(product.getPricePromotional() != 0 ? product.getPricePromotional() : product.getPrice());
			bill.setProductId(product.getId());
			bill.setPromotion(product.getPromotionInformation());
			bill.setQty(product.getQtyInCart());
			listBillDetail.add(bill);
		}
		}
		model.setListBillDetail(listBillDetail);
		
		
		
		Map<String, String> map = null;
		
		if(model.getListBillDetail().size() <= 5) { 
			map = billService.insert(model, false);
		} else {
			map = new HashMap<String, String>();
			map.put("danger", "Bạn chỉ được phép đặt tối đa 5 sản phẩm khác nhau cho 1 đơn hàng");
		}
		Set<String> set = map.keySet();

		for (String key : set) {
			
			if(key.equals("codeConfirm")) {
				Random rand = new Random();
				int code = rand.nextInt(8889) + 1111;
				model.setCodeConfirm(code);
				SendEmail.sendEmailConfirmOrder(code, model.getEmail());
				SessionUtil.getInstance().putValue(request, "Bill", model);
				request.setAttribute("codeConfirm", key);
			} 
			request.setAttribute("alert", key);
			request.setAttribute("message", map.get(key));
		}
		request.setAttribute("billModel", model);
		request.getRequestDispatcher(SystemContain.URL_PAGE_CHECKOUT).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String url = request.getRequestURI();
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
		Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		
		if (user != null) {
			if(url.startsWith(request.getContextPath() + "/thanhtoan") && (cart != null ? cart.list().size() > 0 : cart != null)) {
			url = SystemContain.URL_PAGE_CHECKOUT;
			request.getRequestDispatcher(url).forward(request, response);
			} else if(url.startsWith(request.getContextPath() + "/donhang")){
			request.setAttribute("listBill", billService.findAllByUserId(user.getId()));
			url = SystemContain.URL_PAGE_HISTORYORDER;
			request.getRequestDispatcher(url).forward(request, response);
			} else if (url.startsWith(request.getContextPath() + "/xacnhandon") && (cart != null ? cart.list().size() > 0 : cart != null)){
				int code = 0;
				try {
					 code = Integer.parseInt(request.getParameter("maxacnhan"));
				} catch (Exception e) {
					 code = 0;
				}
				Map<String, String> map = null;
				BillModel bill = (BillModel) SessionUtil.getInstance().getValue(request, "Bill");
				if(code == bill.getCodeConfirm()) {
					bill.setCreateBy(user.getId());
					map = billService.insert(bill, true);
				} else {
					map = new HashMap<String, String>();
					map.put("codeConfirm", "Mã xác nhận không đúng");
				}
				Set<String> set = map.keySet();
				for (String key : set) {
					if(key.equals("success")) {
						for(BillDetailModel p : bill.getListBillDetail()) {
							cart.remove(p.getProductId());
						}
						request.setAttribute("alert", key);
						request.setAttribute("message", map.get(key));
					} else if(key.equals("codeConfirm")) {
						request.setAttribute("codeConfirm", key);
						request.setAttribute("mesCodeConfirm", map.get(key));
					}
					
				}
				request.getRequestDispatcher(SystemContain.URL_PAGE_CHECKOUT).forward(request, response);
			} else if(url.startsWith(request.getContextPath() + "/guilaima") && (cart != null ? cart.list().size() > 0 : cart != null)){
				BillModel bill = (BillModel) SessionUtil.getInstance().getValue(request, "Bill");
				Random rand = new Random();
				int code = rand.nextInt(8889) + 1111;
				bill.setCodeConfirm(code);
				SendEmail.sendEmailConfirmOrder(code, bill.getEmail());
				SessionUtil.getInstance().putValue(request, "Bill", bill);
				request.setAttribute("codeConfirm", "codeConfirm");
				request.getRequestDispatcher(SystemContain.URL_PAGE_CHECKOUT).forward(request, response);
			} else if(url.startsWith(request.getContextPath() + "/huydonhang")){
				String id = request.getParameter("id");
				if(id != null) {
					Map<String, String> map = billService.updateStatus("cancel", id);
					Set<String> set = map.keySet();
					for (String key : set) {
						request.setAttribute("alert", key);
						request.setAttribute("message", map.get(key));
					}

					request.setAttribute("listBill", billService.findAllByUserId(user.getId()));
					url = SystemContain.URL_PAGE_HISTORYORDER;
					request.getRequestDispatcher(url).forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/donhang");
				}
				
				
			} else {
				response.sendRedirect(request.getContextPath() + "/giohang");
			}
			
		} else {
			response.sendRedirect(request.getContextPath() + "/dangnhap");
		}
		
		
	}
}

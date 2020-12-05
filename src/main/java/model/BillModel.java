package model;

import java.util.List;

public class BillModel extends AbstractModel{
	private String userId;
	private String fullname;
	private String email;
	private String phone;
	private String note;
	private String address;
	private int totalPrice;
	private int codeConfirm;
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	private List<BillDetailModel> listBillDetail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BillDetailModel> getListBillDetail() {
		return listBillDetail;
	}

	public void setListBillDetail(List<BillDetailModel> listBillDetail) {
		this.listBillDetail = listBillDetail;
	}

	public int getCodeConfirm() {
		return codeConfirm;
	}

	public void setCodeConfirm(int codeConfirm) {
		this.codeConfirm = codeConfirm;
	}
	
}

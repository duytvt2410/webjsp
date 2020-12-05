package model;

import java.util.List;

import service.imp.ImagesService;

public class BillDetailModel {
	private String billId;
	private String productId;
	private String nameProduct;
	private String urlImageProduct;
	private int price;
	private int qty;
	private String promotion;
	
	// images for product
	private ImagesService imageService = ImagesService.getInstance();
	
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getUrlImageProduct() {
		List<ImagesModel> imgs = imageService.findAllByProductIdAndType(this.productId, "image_product");
		urlImageProduct = imgs != null && imgs.size() > 0 ? imgs.get(0).getPhoto() : null;
		return urlImageProduct;
	}
}

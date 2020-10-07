package model;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class ProductModel extends AbstractModel {
	private Long brandId;
	private int price;
	private int pricePromotional;
	private int qty;
	private int qtyInCart;

	private String brandAlias;
	private String brandName;
	private String categoryAlias;
	

	private String promotionInformation;
	private String description;
	private String specifications;

	private ImagesModel imageProduct;

	private List<FileItem> fieldsImagesDetail;

	private FileItem fieldsImageProduct;
	
	private List<ImagesModel> imagesDetail;
	
	private List<ImagesModel> listImage;
	
	
	private List<ProductClassifyModel> listClassify;

	

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPricePromotional() {
		return pricePromotional;
	}

	public void setPricePromotional(int pricePromotional) {
		this.pricePromotional = pricePromotional;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getBrandAlias() {
		return brandAlias;
	}

	public void setBrandAlias(String brandAlias) {
		this.brandAlias = brandAlias;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryAlias() {
		return categoryAlias;
	}

	public void setCategoryAlias(String categoryAlias) {
		this.categoryAlias = categoryAlias;
	}

	public String getPromotionInformation() {
		return promotionInformation;
	}

	public void setPromotionInformation(String promotionInformation) {
		this.promotionInformation = promotionInformation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public List<FileItem> getFieldsImagesDetail() {
		return fieldsImagesDetail;
	}

	public void setFieldsImagesDetail(List<FileItem> fieldsImagesDetail) {
		this.fieldsImagesDetail = fieldsImagesDetail;
	}

	public FileItem getFieldsImageProduct() {
		return fieldsImageProduct;
	}

	public void setFieldsImageProduct(FileItem fieldsImageProduct) {
		this.fieldsImageProduct = fieldsImageProduct;
	}

	public List<ProductClassifyModel> getListClassify() {
		return listClassify;
	}

	public void setListClassify(List<ProductClassifyModel> listClassify) {
		this.listClassify = listClassify;
	}

	public ImagesModel getImageProduct() {
		for (ImagesModel image : listImage) {
			if (image.getType().equalsIgnoreCase("image_product")) {
				this.imageProduct = image;
				listImage.remove(image);
				break;
			}
		}
		return imageProduct;
	}

	public List<ImagesModel> getImagesDetail() {
		for (ImagesModel image : listImage) {
			if (image.getType().equalsIgnoreCase("image_product")) {
				this.imageProduct = image;
				listImage.remove(image);
				break;
			}
		}

			this.imagesDetail = listImage;
		return imagesDetail;
	}

	public void setListImage(List<ImagesModel> listImage) {
		this.listImage = listImage;
	}

	public String getStringIdClassify() {
		String res = "";
		for (ProductClassifyModel i : this.listClassify) {
			res += i.getClassifyId() + "dt14082410dt";

		}

		return res;
	}
	
	public void updateQtyInCart(int qty) {
		this.qtyInCart += qty;
	}
	
	public void updateQtyInCart() {
		this.qtyInCart++;
	}

	public int getQtyInCart() {
		return qtyInCart;
	}
	
	
}

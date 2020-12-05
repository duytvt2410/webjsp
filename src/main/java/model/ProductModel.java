package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import service.ICommentService;
import service.imp.CommentService;
import service.imp.ImagesService;

public class ProductModel extends AbstractModel {
	// product
	private String brandId;
	private int price;
	private int pricePromotional;
	private int qty;
	private String brandAlias;
	private String brandName;
	private String categoryAlias;
	private String categoryName;
	private String promotionInformation;
	private String description;
	private String specifications;
	private double rated;
	private String isNew;

	// comment review
	private ICommentService commentService = CommentService.getInstance();
	private double star1 = 0, star2 = 0, star3 = 0, star4 = 0, star5 = 0, totalStar = 0;
	List<CommentModel> listComment;

	// images for product
	private ImagesService imageService = ImagesService.getInstance();
	private List<ImagesModel> imagesDetail;
	private ImagesModel imageProduct;
	// cart
	private int qtyInCart;

	// classify for product
	private List<ProductClassifyModel> listClassify;

	// getter and seter

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
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

	public void setImagesDetail(List<ImagesModel> imagesDetail) {
		this.imagesDetail = imagesDetail;
	}

	public void setImageProduct(ImagesModel imageProduct) {
		this.imageProduct = imageProduct;
	}

	public List<ProductClassifyModel> getListClassify() {
		return listClassify;
	}

	public void setListClassify(List<ProductClassifyModel> listClassify) {
		this.listClassify = listClassify;
	}

	public String getUrlImageProduct() {

		List<ImagesModel> imgs = imageService.findAllByProductIdAndType(super.getId(), "image_product");
		
		return imgs != null && imgs.size() > 0 ? imgs.get(0).getPhoto() : null;
	}

	public List<String> getUrlImagesDetail() {

		List<ImagesModel>  imgs = imageService.findAllByProductIdAndType(super.getId(), "image_detail");
		List<String> res = null;

		if (imgs != null && imgs.size() > 0) {
			res = new ArrayList<String>();
			Collections.sort(imgs, new Comparator<ImagesModel>() {
				@Override
				public int compare(ImagesModel c2, ImagesModel c1) {
					int a, b = 0;
					try {
						a = Integer.parseInt(c2.getPhoto().split("-")[c2.getPhoto().split("-").length - 2]);
						b = Integer.parseInt(c1.getPhoto().split("-")[c1.getPhoto().split("-").length - 2]);
					} catch (Exception e) {
						a = 0;
						b = 0;
					}

					return a < b ? -1 : (a > b) ? 1 : 0;
				}
			});
		}
		
		for(ImagesModel img: imgs) {
			res.add(img.getPhoto());
		}

		return res;
	}

	public List<ImagesModel> getImagesDetail() {
		return imagesDetail;
	}

	public ImagesModel getImageProduct() {
		return imageProduct;
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
		if (qtyInCart < 0)
			qtyInCart = 1;
		if (qtyInCart > 3)
			qtyInCart = 3;
		return qtyInCart;
	}

	public List<CommentModel> getListComment() {
		if (this.listComment == null) {
			this.listComment = commentService.findAllByProductId(super.getId());
			for (CommentModel c : listComment) {
				switch (c.getStar()) {
				case 1:
					star1++;
					break;
				case 2:
					star2++;
					break;
				case 3:
					star3++;
					break;
				case 4:
					star4++;
					break;
				case 5:
					star5++;
					break;
				}
				totalStar++;
			}
		}
		return listComment;
	}

	public double getRatingAvg() {
		double avg = (star5 * 5 + star4 * 4 + star3 * 3 + star2 * 2 + star1 * 1) / (totalStar);
		return Math.floor(avg * 10) / 10;
	}

	public double getTotalStar() {
		return totalStar;
	}

	public double getStar1() {
		return star1;
	}

	public double getStar2() {
		return star2;
	}

	public double getStar3() {
		return star3;
	}

	public double getStar4() {
		return star4;
	}

	public double getStar5() {
		return star5;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getRated() {
		return rated;
	}

	public void setRated(double rated) {
		this.rated = rated;
	}

	public String getIsNew() {
		if(isNew == null) isNew = "";
		return isNew;
	}

	public void setIsNew(String isNew) {
		
		this.isNew = isNew;
	}

}

package model;

public class ProductClassifyModel extends AbstractModel{
	private Long productId;
	
	private Long classifyId;
	
	private String productAlias;
	
	public String getProductAlias() {
		return productAlias;
	}
	public void setProductAlias(String productAlias) {
		this.productAlias = productAlias;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	
	
}

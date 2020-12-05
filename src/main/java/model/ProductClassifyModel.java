package model;

public class ProductClassifyModel extends AbstractModel{
	private String productId;
	
	private String classifyId;
	
	private String classifyName;
	
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	private String productAlias;
	
	public String getProductAlias() {
		return productAlias;
	}
	public void setProductAlias(String productAlias) {
		this.productAlias = productAlias;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}
	
	
}

package model;

public class ImagesModel extends AbstractModel{
	private Long product_Id;
	private String type;
	private String url;
	public Long getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(Long product_Id) {
		this.product_Id = product_Id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

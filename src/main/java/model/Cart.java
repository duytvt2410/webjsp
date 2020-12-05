package model;

import java.util.Collection;
import java.util.HashMap;

public class Cart {
	private HashMap<String, ProductModel> data;

	public Cart() {
		this.data = new HashMap<String, ProductModel>();
	}

	public ProductModel get(String id) {
		return data.get(id);
	}

	public int put(ProductModel item, int quantity) {
		if (data.containsKey(item.getId())) {
			data.get(item.getId()).updateQtyInCart(quantity);;
		} else {
			item.updateQtyInCart(quantity);;
			data.put(item.getId(), item);
		}
		return data.get(item.getId()).getQtyInCart();
	}
	// xoa sp
	public boolean remove(String id) {
		return data.remove(id) == null;
	}

	// tinh tong tien
	public int total() {
		int sum = 0;
		for (ProductModel p : data.values()) {
			if(p.getPricePromotional() != 0) {
				sum += (p.getQtyInCart() * p.getPricePromotional());
			} else {
				sum += (p.getQtyInCart() * p.getPrice());
			}
			
		}
		return sum;
	}

	public Collection<ProductModel> list() {
		return data.values();
	}
	
}

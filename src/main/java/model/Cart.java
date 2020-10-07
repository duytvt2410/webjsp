package model;

import java.util.Collection;
import java.util.HashMap;

public class Cart {
	private HashMap<Long, ProductModel> data;

	public Cart() {
		this.data = new HashMap<Long, ProductModel>();
	}

	public ProductModel get(Long id) {
		return data.get(id);
	}

	public int put(ProductModel item) {
		if (data.containsKey(item.getId())) {
			data.get(item.getId()).updateQtyInCart();;
		} else {
			item.updateQtyInCart();
			data.put(item.getId(), item);
		}
		return data.get(item.getId()).getQty();
	}

	public int put(Long id, int quantity) {
		if (data.containsKey(id))
			data.get(id).updateQtyInCart(quantity);
		return data.get(id).getQtyInCart();
	}

	// xoa sp
	public boolean remove(Long id) {
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

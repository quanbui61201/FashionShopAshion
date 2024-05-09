package vn.devpro.javaweb27.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

	public BigInteger totalCartProduct() {
		BigInteger total = BigInteger.ZERO;
		for (CartProduct cartProduct : cartProducts) {
			total = total.add(cartProduct.getQuantity());
		}
		return total;
	}
	
	public BigDecimal totalPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartProduct cartProduct : cartProducts) {
			total = total.add(cartProduct.totalPrice());
		}
		return total;
	}
	
	public int findCartProductById(int id) {
		for (int index = 0; index < cartProducts.size(); index++) {
			if (cartProducts.get(index).getProductId() == id) {
				return index;
			}
		}
		return -1;
	}

	public int findCartProductByIdAndSize(int id, String size) {
		for (int index = 0; index < cartProducts.size(); index++) {
			if (cartProducts.get(index).getProductId() == id && cartProducts.get(index).getSize() == size) {
				return index;
			}
		}
		return -1;
	}

	public Cart() {
		super();
	}

	public Cart(List<CartProduct> cartProducts) {
		super();
		this.cartProducts = cartProducts;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	

}

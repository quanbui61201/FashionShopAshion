package vn.devpro.javaweb27.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CartProduct {
	
	private int productId;
	private BigInteger quantity;
	private String productName;
	private String avatar;
	private String size;
	private BigDecimal price;
	
	public BigDecimal totalPrice () {
		if (this.price == null || this.quantity == null) {
			return BigDecimal.ZERO;
		}
		return this.price.multiply(new BigDecimal(this.quantity));
	}
	
	public CartProduct() {
		super();
	}
	
	public CartProduct(int productId, BigInteger quantity, String productName, String avatar, String size,
			BigDecimal price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.productName = productName;
		this.avatar = avatar;
		this.size = size;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public BigInteger getQuantity() {
		return quantity;
	}
	
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}

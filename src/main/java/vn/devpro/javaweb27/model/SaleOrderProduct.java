package vn.devpro.javaweb27.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order_product")
public class SaleOrderProduct extends BaseModel {

	@Column(name = "quantity", nullable = true)
	private Integer quantity;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

	@Column(name = "size", length = 5, nullable = true)
	private String size;

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_product
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_sale_order
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_order_id")
	private SaleOrder saleOrder;

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_user (for create order product)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateSaleOrderProduct;

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_user (for update order product)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateSaleOrderProduct;

	public SaleOrderProduct() {
		super();
	}

	public SaleOrderProduct(Integer id, Date createDate, Date updateDate, Boolean status, Integer quantity,
			String description, String size, Product product, SaleOrder saleOrder,
			User userCreateSaleOrderProduct, User userUpdateSaleOrderProduct) {
		super(id, createDate, updateDate, status);
		this.quantity = quantity;
		this.description = description;
		this.size = size;
		this.product = product;
		this.saleOrder = saleOrder;
		this.userCreateSaleOrderProduct = userCreateSaleOrderProduct;
		this.userUpdateSaleOrderProduct = userUpdateSaleOrderProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public User getUserCreateSaleOrderProduct() {
		return userCreateSaleOrderProduct;
	}

	public void setUserCreateSaleOrderProduct(User userCreateSaleOrderProduct) {
		this.userCreateSaleOrderProduct = userCreateSaleOrderProduct;
	}

	public User getUserUpdateSaleOrderProduct() {
		return userUpdateSaleOrderProduct;
	}

	public void setUserUpdateSaleOrderProduct(User userUpdateSaleOrderProduct) {
		this.userUpdateSaleOrderProduct = userUpdateSaleOrderProduct;
	}

}

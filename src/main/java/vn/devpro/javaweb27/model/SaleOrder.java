package vn.devpro.javaweb27.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order")
public class SaleOrder extends BaseModel {

	@Column(name = "code", length = 60, nullable = false)
	private String code;

	@Column(name = "total", nullable = true)
	private BigDecimal total;

	@Column(name = "customer_name", length = 300, nullable = false)
	private String customerName;

	@Column(name = "customer_mobile", length = 60, nullable = true)
	private String customerMobile;

	@Column(name = "customer_email", length = 120, nullable = true)
	private String customerEmail;

	@Column(name = "customer_address", length = 300, nullable = true)
	private String customerAddress;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	// Mapping many-to-one: tbl_sale_order-to-tbl_user (for create order)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateSaleOrder;

	// Mapping many-to-one: tbl_sale_order-to-tbl_user (for update order)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateSaleOrder;

	// Mapping one-to-many: tbl_sale_order-to-tbl_sale_order_product
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<SaleOrderProduct> orderProducts = new HashSet<SaleOrderProduct>();

	public void addRelationalSaleOrderProduct(SaleOrderProduct orderProduct) {
		orderProducts.add(orderProduct);
		orderProduct.setSaleOrder(this);
	}

	public void removeRelationalSaleOrderProduct(SaleOrderProduct orderProduct) {
		orderProducts.remove(orderProduct);
		orderProduct.setSaleOrder(null);
	}
	// -----------------------------------------------------------------------

	public SaleOrder() {
		super();
	}

	public SaleOrder(Integer id, Date createDate, Date updateDate, Boolean status, String code, BigDecimal total,
			String customerName, String customerMobile, String customerEmail, String customerAddress, User user,
			User userCreateSaleOrder, User userUpdateSaleOrder) {
		super(id, createDate, updateDate, status);
		this.code = code;
		this.total = total;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.user = user;
		this.userCreateSaleOrder = userCreateSaleOrder;
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserCreateSaleOrder() {
		return userCreateSaleOrder;
	}

	public void setUserCreateSaleOrder(User userCreateSaleOrder) {
		this.userCreateSaleOrder = userCreateSaleOrder;
	}

	public User getUserUpdateSaleOrder() {
		return userUpdateSaleOrder;
	}

	public void setUserUpdateSaleOrder(User userUpdateSaleOrder) {
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}

	public Set<SaleOrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<SaleOrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

}

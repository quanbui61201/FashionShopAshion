package vn.devpro.javaweb27.model;

import java.util.HashSet;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbl_user")
public class User extends BaseModel implements UserDetails {

	@Column(name = "username", length = 120, nullable = false)
	private String username;

	@Column(name = "password", length = 120, nullable = false)
	private String password;

	@Column(name = "name", length = 120, nullable = true)
	private String name;

	@Column(name = "mobile", length = 60, nullable = true)
	private String mobile;

	@Column(name = "email", length = 200, nullable = true)
	private String email;

	@Column(name = "address", length = 300, nullable = true)
	private String address;

	@Column(name = "avatar", length = 300, nullable = true)
	private String avatar;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

	@Column(name = "create_by")
	private Integer createBy;

	@Column(name = "update_by")
    private Integer updateBy;

	// Mapping many-to-many: tbl_user-to-tbl_role
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private Set<Role> roles = new HashSet<Role>();

	public void addRelationalUserRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}

	public void removeRelationalUserRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}
	// ------------------------------------------------------------------

	// Mapping many-to-many: tbl_user-to-tbl_product
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "wishlistUsers")
	private Set<Product> wishlistProducts = new HashSet<Product>();

	public void addRelationalWishlistProduct(Product product) {
		if (!wishlistProducts.contains(product)) {
			product.getWishlishUsers().add(this);
			wishlistProducts.add(product);
		}
	}

	public void removeRelationalWishlistProduct(Product product) {
		if (wishlistProducts.contains(product)) {
			product.getWishlishUsers().remove(this);
			wishlistProducts.remove(product);
		}
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_order
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SaleOrder> orders = new HashSet<SaleOrder>();

	public void addRelationalSaleOrder(SaleOrder order) {
		orders.add(order);
		order.setUser(this);
	}

	public void removeRelationalSaleOrder(SaleOrder order) {
		orders.remove(order);
		order.setUser(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_category (user create category)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateCategory")
	private Set<Category> userCreateCategories = new HashSet<Category>();

	public void addRelationalUserCreateCategory(Category category) {
		userCreateCategories.add(category);
		category.setUserCreateCategory(this);
	}

	public void removeRelationalUserCreateCategory(Category category) {
		userCreateCategories.remove(category);
		category.setUserCreateCategory(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_category (user update category)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateCategory")
	private Set<Category> userUpdateCategories = new HashSet<Category>();

	public void addRelationalUserUpdateCategory(Category category) {
		userUpdateCategories.add(category);
		category.setUserUpdateCategory(this);
	}

	public void removeRelationalUserUpdateCategory(Category category) {
		userUpdateCategories.remove(category);
		category.setUserUpdateCategory(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_contact (user create contact)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateContact")
	private Set<Contact> userCreateContacts = new HashSet<Contact>();

	public void addRelationalUserCreateContact(Contact contact) {
		userCreateContacts.add(contact);
		contact.setUserCreateContact(this);
	}

	public void removeRelationalUserCreateContact(Contact contact) {
		userCreateContacts.remove(contact);
		contact.setUserCreateContact(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_contact (user update contact)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateContact")
	private Set<Contact> userUpdateContacts = new HashSet<Contact>();

	public void addRelationalUserUpdateContact(Contact contact) {
		userUpdateContacts.add(contact);
		contact.setUserUpdateContact(this);
	}

	public void removeRelationalUserUpdateContact(Contact contact) {
		userUpdateContacts.remove(contact);
		contact.setUserUpdateContact(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_product (user create product)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProduct")
	private Set<Product> userCreateProducts = new HashSet<Product>();

	public void addRelationalUserCreateProduct(Product product) {
		userCreateProducts.add(product);
		product.setUserCreateProduct(this);
	}

	public void removeRelationalUserCreateProduct(Product product) {
		userCreateProducts.remove(product);
		product.setUserCreateProduct(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_product (user update product)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProduct")
	private Set<Product> userUpdateProducts = new HashSet<Product>();

	public void addRelationalUserUpdateProduct(Product product) {
		userUpdateProducts.add(product);
		product.setUserUpdateProduct(this);
	}

	public void removeRelationalUserUpdateProduct(Product product) {
		userUpdateProducts.remove(product);
		product.setUserUpdateProduct(null);
	}
	// -----------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_product_mage (user create product image)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProductImage")
	private Set<ProductImage> userCreateProductImages = new HashSet<ProductImage>();

	public void addRelationalUserCreateProductImage(ProductImage productImage) {
		userCreateProductImages.add(productImage);
		productImage.setUserCreateProductImage(this);
	}

	public void removeRelationalUserCreateProductImage(ProductImage productImage) {
		userCreateProductImages.remove(productImage);
		productImage.setUserCreateProductImage(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_product_image (user update product image)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProductImage")
	private Set<ProductImage> userUpdateProductImages = new HashSet<ProductImage>();

	public void addRelationalUserUpdateProductImage(ProductImage productImage) {
		userUpdateProductImages.add(productImage);
		productImage.setUserUpdateProductImage(this);
	}

	public void removeRelationalUserUpdateProductImage(ProductImage productImage) {
		userUpdateProductImages.remove(productImage);
		productImage.setUserUpdateProductImage(null);
	}
	// -----------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_role (user create role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateRole")
	private Set<Role> userCreateRoles = new HashSet<Role>();

	public void addRelationalUserCreateRole(Role role) {
		userCreateRoles.add(role);
		role.setUserCreateRole(this);
	}

	public void removeRelationalUserCreateRole(Role role) {
		userCreateRoles.remove(role);
		role.setUserCreateRole(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_role (user update role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateRole")
	private Set<Role> userUpdateRoles = new HashSet<Role>();

	public void addRelationalUserUpdateRole(Role role) {
		userUpdateRoles.add(role);
		role.setUserUpdateRole(this);
	}

	public void removeRelationalUserUpdateRole(Role role) {
		userUpdateRoles.remove(role);
		role.setUserUpdateRole(null);
	}
	// -----------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_sale_order (user create order)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrder")
	private Set<SaleOrder> userCreateSaleOrders = new HashSet<SaleOrder>();

	public void addRelationalUserCreateSaleOrder(SaleOrder saleOrder) {
		userCreateSaleOrders.add(saleOrder);
		saleOrder.setUserCreateSaleOrder(this);
	}

	public void removeRelationalUserCreateSaleOrder(SaleOrder saleOrder) {
		userCreateSaleOrders.remove(saleOrder);
		saleOrder.setUserCreateSaleOrder(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_sale_order (user update order)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrder")
	private Set<SaleOrder> userUpdateSaleOrders = new HashSet<SaleOrder>();

	public void addRelationalUserUpdateSaleOrder(SaleOrder saleOrder) {
		userUpdateSaleOrders.add(saleOrder);
		saleOrder.setUserUpdateSaleOrder(this);
	}

	public void removeRelationalUserUpdateSaleOrder(SaleOrder saleOrder) {
		userUpdateSaleOrders.remove(saleOrder);
		saleOrder.setUserUpdateSaleOrder(null);
	}
	// -----------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_sale_order_product (user create order product)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrderProduct")
	private Set<SaleOrderProduct> userCreateSaleOrderProducts = new HashSet<SaleOrderProduct>();

	public void addRelationalUserCreateSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		userCreateSaleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setUserCreateSaleOrderProduct(this);
	}

	public void removeRelationalUserCreateSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		userCreateSaleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setUserCreateSaleOrderProduct(null);
	}
	// ------------------------------------------------------------------

	// Mapping one-to-many: tbl_user-to-tbl_sale_order_product (user update order product)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrderProduct")
	private Set<SaleOrderProduct> userUpdateSaleOrderProducts = new HashSet<SaleOrderProduct>();

	public void addRelationalUserUpdateSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		userUpdateSaleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setUserUpdateSaleOrderProduct(this);
	}

	public void removeRelationalUserUpdateSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		userUpdateSaleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setUserUpdateSaleOrderProduct(null);
	}
	// -----------------------------------------------------------------------

	public User() {
		super();
	}

	public User(Integer id, Date createDate, Date updateDate, Boolean status, String username, String password,
			String name, String mobile, String email, String address, String avatar, String description,
			Integer createBy, Integer updateBy) {
		super(id, createDate, updateDate, status);
		this.username = username;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.avatar = avatar;
		this.description = description;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Product> getWishlistProducts() {
		return wishlistProducts;
	}

	public void setWishlistProducts(Set<Product> wishlistProducts) {
		this.wishlistProducts = wishlistProducts;
	}

	public Set<SaleOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<SaleOrder> orders) {
		this.orders = orders;
	}

	public Set<Category> getUserCreateCategories() {
		return userCreateCategories;
	}

	public void setUserCreateCategories(Set<Category> userCreateCategories) {
		this.userCreateCategories = userCreateCategories;
	}

	public Set<Category> getUserUpdateCategories() {
		return userUpdateCategories;
	}

	public void setUserUpdateCategories(Set<Category> userUpdateCategories) {
		this.userUpdateCategories = userUpdateCategories;
	}

	public Set<Contact> getUserCreateContacts() {
		return userCreateContacts;
	}

	public void setUserCreateContacts(Set<Contact> userCreateContacts) {
		this.userCreateContacts = userCreateContacts;
	}

	public Set<Contact> getUserUpdateContacts() {
		return userUpdateContacts;
	}

	public void setUserUpdateContacts(Set<Contact> userUpdateContacts) {
		this.userUpdateContacts = userUpdateContacts;
	}

	public Set<Product> getUserCreateProducts() {
		return userCreateProducts;
	}

	public void setUserCreateProducts(Set<Product> userCreateProducts) {
		this.userCreateProducts = userCreateProducts;
	}

	public Set<Product> getUserUpdateProducts() {
		return userUpdateProducts;
	}

	public void setUserUpdateProducts(Set<Product> userUpdateProducts) {
		this.userUpdateProducts = userUpdateProducts;
	}

	public Set<ProductImage> getUserCreateProductImages() {
		return userCreateProductImages;
	}

	public void setUserCreateProductImages(Set<ProductImage> userCreateProductImages) {
		this.userCreateProductImages = userCreateProductImages;
	}

	public Set<ProductImage> getUserUpdateProductImages() {
		return userUpdateProductImages;
	}

	public void setUserUpdateProductImages(Set<ProductImage> userUpdateProductImages) {
		this.userUpdateProductImages = userUpdateProductImages;
	}

	public Set<Role> getUserCreateRoles() {
		return userCreateRoles;
	}

	public void setUserCreateRoles(Set<Role> userCreateRoles) {
		this.userCreateRoles = userCreateRoles;
	}

	public Set<Role> getUserUpdateRoles() {
		return userUpdateRoles;
	}

	public void setUserUpdateRoles(Set<Role> userUpdateRoles) {
		this.userUpdateRoles = userUpdateRoles;
	}

	public Set<SaleOrder> getUserCreateSaleOrders() {
		return userCreateSaleOrders;
	}

	public void setUserCreateSaleOrders(Set<SaleOrder> userCreateSaleOrders) {
		this.userCreateSaleOrders = userCreateSaleOrders;
	}

	public Set<SaleOrder> getUserUpdateSaleOrders() {
		return userUpdateSaleOrders;
	}

	public void setUserUpdateSaleOrders(Set<SaleOrder> userUpdateSaleOrders) {
		this.userUpdateSaleOrders = userUpdateSaleOrders;
	}

	public Set<SaleOrderProduct> getUserCreateSaleOrderProducts() {
		return userCreateSaleOrderProducts;
	}

	public void setUserCreateSaleOrderProducts(Set<SaleOrderProduct> userCreateSaleOrderProducts) {
		this.userCreateSaleOrderProducts = userCreateSaleOrderProducts;
	}

	public Set<SaleOrderProduct> getUserUpdateSaleOrderProducts() {
		return userUpdateSaleOrderProducts;
	}

	public void setUserUpdateSaleOrderProducts(Set<SaleOrderProduct> userUpdateSaleOrderProducts) {
		this.userUpdateSaleOrderProducts = userUpdateSaleOrderProducts;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

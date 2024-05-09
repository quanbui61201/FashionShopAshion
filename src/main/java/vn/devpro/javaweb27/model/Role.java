package vn.devpro.javaweb27.model;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseModel implements GrantedAuthority {

	@Column(name = "name", length = 200, nullable = true)
	private String name;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

	// Mapping many-to-one: tbl_role-to-tbl_user (for create role)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateRole;

	// Mapping many-to-one: tbl_role-to-tbl_user (for update role)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateRole;

	// Mapping many-to-many: tbl_role-to-tbl_user
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<User>();

	public Role() {
		super();
	}

	public Role(Integer id, Date createDate, Date updateDate, Boolean status, String name, String description,
			User userCreateRole, User userUpdateRole) {
		super(id, createDate, updateDate, status);
		this.name = name;
		this.description = description;
		this.userCreateRole = userCreateRole;
		this.userUpdateRole = userUpdateRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUserCreateRole() {
		return userCreateRole;
	}

	public void setUserCreateRole(User userCreateRole) {
		this.userCreateRole = userCreateRole;
	}

	public User getUserUpdateRole() {
		return userUpdateRole;
	}

	public void setUserUpdateRole(User userUpdateRole) {
		this.userUpdateRole = userUpdateRole;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}

}

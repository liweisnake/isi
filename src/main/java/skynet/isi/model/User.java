package skynet.isi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String regionId;
	private String customType;
	private String company;
	private String mobile;
	private String phone;
	private String email;
	private String qq;
	private String address;
	private String loginName;
	private String password;
	private Integer credit = 0;
	private Boolean isActive;
	private String position;
	private String function;
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<CreditHistory> creditHistories = new HashSet<CreditHistory>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String regionId, String customType, String company,
			String mobile, String phone, String email, String qq,
			String address, String loginName, String password, Integer credit,
			Boolean isActive, String position, String function,
			Set<Order> orders, Set<CreditHistory> creditHistories,
			Set<ShoppingCart> shoppingCarts, Set<Favorite> favorites,
			Set<Comment> comments, Set<UserRole> userRoles) {
		this.regionId = regionId;
		this.customType = customType;
		this.company = company;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.qq = qq;
		this.address = address;
		this.loginName = loginName;
		this.password = password;
		this.credit = credit;
		this.isActive = isActive;
		this.position = position;
		this.function = function;
		this.orders = orders;
		this.creditHistories = creditHistories;
		this.shoppingCarts = shoppingCarts;
		this.favorites = favorites;
		this.comments = comments;
		this.userRoles = userRoles;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "region_id")
	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	@Column(name = "custom_type")
	public String getCustomType() {
		return this.customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	@Column(name = "company", length = 63)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "mobile", length = 45)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "qq", length = 45)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "address", length = 127)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "login_name", length = 45)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password", length = 127)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "credit")
	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "position", length = 60)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "function")
	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<CreditHistory> getCreditHistories() {
		return this.creditHistories;
	}

	public void setCreditHistories(Set<CreditHistory> creditHistories) {
		this.creditHistories = creditHistories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
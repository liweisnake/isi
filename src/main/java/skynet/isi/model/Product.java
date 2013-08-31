package skynet.isi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private Manufacturer manufacturer;
	private Category category;
	private String iicSku;
	private String distySku;
	private String mfrSku;
	private String description;
	private String longDesc;
	private String classCode;
	private String uom;
	private Double packSize;
	private String rohsStatus;
	private String dataSheet;
	private String imageUrl;
	private String countryOrigin;
	private String store;
	private Double weight;
	private Double oneAmount;
	private Double onePrice;
	private Double twoAmount;
	private Double twoPrice;
	private Double threeAmount;
	private Double threePrice;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);
	private Set<Promotion> promotions = new HashSet<Promotion>(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(Manufacturer manufacturer, Category category, String iicSku,
			String distySku, String mfrSku, String description,
			String longDesc, String classCode, String uom, Double packSize,
			String rohsStatus, String dataSheet, String imageUrl,
			String countryOrigin, String store, Double weight,
			Double oneAmount, Double onePrice, Double twoAmount,
			Double twoPrice, Double threeAmount, Double threePrice,
			Set<Comment> comments, Set<OrderItem> orderItems,
			Set<Favorite> favorites, Set<ShoppingCart> shoppingCarts,
			Set<Promotion> promotions) {
		this.manufacturer = manufacturer;
		this.category = category;
		this.iicSku = iicSku;
		this.distySku = distySku;
		this.mfrSku = mfrSku;
		this.description = description;
		this.longDesc = longDesc;
		this.classCode = classCode;
		this.uom = uom;
		this.packSize = packSize;
		this.rohsStatus = rohsStatus;
		this.dataSheet = dataSheet;
		this.imageUrl = imageUrl;
		this.countryOrigin = countryOrigin;
		this.store = store;
		this.weight = weight;
		this.oneAmount = oneAmount;
		this.onePrice = onePrice;
		this.twoAmount = twoAmount;
		this.twoPrice = twoPrice;
		this.threeAmount = threeAmount;
		this.threePrice = threePrice;
		this.comments = comments;
		this.orderItems = orderItems;
		this.favorites = favorites;
		this.shoppingCarts = shoppingCarts;
		this.promotions = promotions;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mfr_id")
	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "iic_sku", length = 45)
	public String getIicSku() {
		return this.iicSku;
	}

	public void setIicSku(String iicSku) {
		this.iicSku = iicSku;
	}

	@Column(name = "disty_sku", length = 45)
	public String getDistySku() {
		return this.distySku;
	}

	public void setDistySku(String distySku) {
		this.distySku = distySku;
	}

	@Column(name = "mfr_sku", length = 45)
	public String getMfrSku() {
		return this.mfrSku;
	}

	public void setMfrSku(String mfrSku) {
		this.mfrSku = mfrSku;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "long_desc", length = 65535)
	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	@Column(name = "class_code", length = 45)
	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@Column(name = "uom", length = 63)
	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	@Column(name = "pack_size", precision = 12, scale = 3)
	public Double getPackSize() {
		return this.packSize;
	}

	public void setPackSize(Double packSize) {
		this.packSize = packSize;
	}

	@Column(name = "rohs_status", length = 100)
	public String getRohsStatus() {
		return this.rohsStatus;
	}

	public void setRohsStatus(String rohsStatus) {
		this.rohsStatus = rohsStatus;
	}

	@Column(name = "data_sheet", length = 700)
	public String getDataSheet() {
		return this.dataSheet;
	}

	public void setDataSheet(String dataSheet) {
		this.dataSheet = dataSheet;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "country_origin", length = 63)
	public String getCountryOrigin() {
		return this.countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	@Column(name = "store", length = 10)
	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Column(name = "weight", precision = 12, scale = 3)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "one_amount", precision = 12, scale = 3)
	public Double getOneAmount() {
		return this.oneAmount;
	}

	public void setOneAmount(Double oneAmount) {
		this.oneAmount = oneAmount;
	}

	@Column(name = "one_price", precision = 12, scale = 3)
	public Double getOnePrice() {
		return this.onePrice;
	}

	public void setOnePrice(Double onePrice) {
		this.onePrice = onePrice;
	}

	@Column(name = "two_amount", precision = 12, scale = 3)
	public Double getTwoAmount() {
		return this.twoAmount;
	}

	public void setTwoAmount(Double twoAmount) {
		this.twoAmount = twoAmount;
	}

	@Column(name = "two_price", precision = 12, scale = 3)
	public Double getTwoPrice() {
		return this.twoPrice;
	}

	public void setTwoPrice(Double twoPrice) {
		this.twoPrice = twoPrice;
	}

	@Column(name = "three_amount", precision = 12, scale = 3)
	public Double getThreeAmount() {
		return this.threeAmount;
	}

	public void setThreeAmount(Double threeAmount) {
		this.threeAmount = threeAmount;
	}

	@Column(name = "three_price", precision = 12, scale = 3)
	public Double getThreePrice() {
		return this.threePrice;
	}

	public void setThreePrice(Double threePrice) {
		this.threePrice = threePrice;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

}
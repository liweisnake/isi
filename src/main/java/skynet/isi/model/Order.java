package skynet.isi.model;

import java.util.Date;
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
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_record")
public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String addressLabel;
	private String addressRegion;
	private String addressDetail;
	private String zipCode;
	private String receiver;
	private String mobile;
	private String telephone;
	private String shippingMethod;
	private String payMethod;
	private String invoiceTitle;
	private String taxpayerNo;
	private String invoiceAddress;
	private String invoicePhone;
	private String bank;
	private String account;
	private String invoiceType;
	private Double preTaxPrice;
	private Double valueAddedTax;
	private Double freight;
	private Double totalPrice;
	private Date orderDate;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(User user) {
		this.user = user;
	}

	/** full constructor */
	public Order(User user, String addressLabel, String addressRegion,
			String addressDetail, String zipCode, String receiver,
			String mobile, String telephone, String shippingMethod,
			String payMethod, String invoiceTitle, String taxpayerNo,
			String invoiceAddress, String invoicePhone, String bank,
			String account, String invoiceType, Double preTaxPrice,
			Double valueAddedTax, Double freight, Double totalPrice,
			Date orderDate, Set<OrderItem> orderItems) {
		this.user = user;
		this.addressLabel = addressLabel;
		this.addressRegion = addressRegion;
		this.addressDetail = addressDetail;
		this.zipCode = zipCode;
		this.receiver = receiver;
		this.mobile = mobile;
		this.telephone = telephone;
		this.shippingMethod = shippingMethod;
		this.payMethod = payMethod;
		this.invoiceTitle = invoiceTitle;
		this.taxpayerNo = taxpayerNo;
		this.invoiceAddress = invoiceAddress;
		this.invoicePhone = invoicePhone;
		this.bank = bank;
		this.account = account;
		this.invoiceType = invoiceType;
		this.preTaxPrice = preTaxPrice;
		this.valueAddedTax = valueAddedTax;
		this.freight = freight;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
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
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "address_label", length = 45)
	public String getAddressLabel() {
		return this.addressLabel;
	}

	public void setAddressLabel(String addressLabel) {
		this.addressLabel = addressLabel;
	}

	@Column(name = "address_region", length = 45)
	public String getAddressRegion() {
		return this.addressRegion;
	}

	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}

	@Column(name = "address_detail", length = 45)
	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	@Column(name = "zip_code", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "receiver", length = 45)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "mobile", length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "telephone", length = 15)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "shipping_method", length = 45)
	public String getShippingMethod() {
		return this.shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	@Column(name = "pay_method", length = 45)
	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Column(name = "invoice_title", length = 45)
	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	@Column(name = "taxpayer_no", length = 45)
	public String getTaxpayerNo() {
		return this.taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}

	@Column(name = "invoice_address", length = 45)
	public String getInvoiceAddress() {
		return this.invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	@Column(name = "invoice_phone", length = 45)
	public String getInvoicePhone() {
		return this.invoicePhone;
	}

	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}

	@Column(name = "bank", length = 45)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "account", length = 45)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "invoice_type", length = 45)
	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Column(name = "pre_tax_price", precision = 12, scale = 3)
	public Double getPreTaxPrice() {
		return this.preTaxPrice;
	}

	public void setPreTaxPrice(Double preTaxPrice) {
		this.preTaxPrice = preTaxPrice;
	}

	@Column(name = "value_added_tax", precision = 12, scale = 3)
	public Double getValueAddedTax() {
		return this.valueAddedTax;
	}

	public void setValueAddedTax(Double valueAddedTax) {
		this.valueAddedTax = valueAddedTax;
	}

	@Column(name = "freight", precision = 12, scale = 3)
	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	@Column(name = "total_price", precision = 12, scale = 3)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "order_date", length = 19)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
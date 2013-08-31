package skynet.isi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private Order order;
	private Product product;
	private Integer orderNum;
	private Double unitPrice;

	// Constructors

	/** default constructor */
	public OrderItem() {
	}

	/** minimal constructor */
	public OrderItem(Order order, Product product) {
		this.order = order;
		this.product = product;
	}

	/** full constructor */
	public OrderItem(Order order, Product product, Integer orderNum,
			Double unitPrice) {
		this.order = order;
		this.product = product;
		this.orderNum = orderNum;
		this.unitPrice = unitPrice;
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
	@JoinColumn(name = "order_id", nullable = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "order_num")
	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "unit_price", precision = 12, scale = 3)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
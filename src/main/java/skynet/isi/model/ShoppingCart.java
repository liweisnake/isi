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
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Product product;
	private Integer intentNum;

	// Constructors

	/** default constructor */
	public ShoppingCart() {
	}

	/** minimal constructor */
	public ShoppingCart(User user, Product product) {
		this.user = user;
		this.product = product;
	}

	/** full constructor */
	public ShoppingCart(User user, Product product, Integer intentNum) {
		this.user = user;
		this.product = product;
		this.intentNum = intentNum;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "intent_num")
	public Integer getIntentNum() {
		return this.intentNum;
	}

	public void setIntentNum(Integer intentNum) {
		this.intentNum = intentNum;
	}

}
package skynet.isi.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Product product;
	private String comment;
	private Date commentDate;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Product product) {
		this.user = user;
		this.product = product;
	}

	/** full constructor */
	public Comment(User user, Product product, String comment, Date commentDate) {
		this.user = user;
		this.product = product;
		this.comment = comment;
		this.commentDate = commentDate;
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

	@Column(name = "comment", length = 511)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "comment_date", length = 19)
	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
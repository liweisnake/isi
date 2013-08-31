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
 * CreditHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "credit_history")
public class CreditHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Date createDate;
	private Integer usedCredit;
	private Integer leftCredit;

	// Constructors

	/** default constructor */
	public CreditHistory() {
	}

	/** full constructor */
	public CreditHistory(User user, Date createDate, Integer usedCredit,
			Integer leftCredit) {
		this.user = user;
		this.createDate = createDate;
		this.usedCredit = usedCredit;
		this.leftCredit = leftCredit;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "used_credit")
	public Integer getUsedCredit() {
		return this.usedCredit;
	}

	public void setUsedCredit(Integer usedCredit) {
		this.usedCredit = usedCredit;
	}

	@Column(name = "left_credit")
	public Integer getLeftCredit() {
		return this.leftCredit;
	}

	public void setLeftCredit(Integer leftCredit) {
		this.leftCredit = leftCredit;
	}

}
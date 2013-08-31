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
 * Promotion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "promotion")
public class Promotion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Product product;
	private Integer banner;
	private String titlePicUrl;
	private String picUrl;
	private String description;
	private String link;
	private Date startTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public Promotion() {
	}

	/** full constructor */
	public Promotion(Product product, Integer banner, String titlePicUrl,
			String picUrl, String description, String link, Date startTime,
			Date endTime) {
		this.product = product;
		this.banner = banner;
		this.titlePicUrl = titlePicUrl;
		this.picUrl = picUrl;
		this.description = description;
		this.link = link;
		this.startTime = startTime;
		this.endTime = endTime;
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
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "banner")
	public Integer getBanner() {
		return this.banner;
	}

	public void setBanner(Integer banner) {
		this.banner = banner;
	}

	@Column(name = "title_pic_url", length = 100)
	public String getTitlePicUrl() {
		return this.titlePicUrl;
	}

	public void setTitlePicUrl(String titlePicUrl) {
		this.titlePicUrl = titlePicUrl;
	}

	@Column(name = "pic_url", length = 100)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "link", length = 100)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "start_time", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
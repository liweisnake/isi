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
 * Manufacturer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manufacturer")
public class Manufacturer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mfrCode;
	private String mfrName;
	private String description;
	private String website;
	private Set<Product> products = new HashSet<Product>(0);

	// Constructors

	/** default constructor */
	public Manufacturer() {
	}

	/** full constructor */
	public Manufacturer(String mfrCode, String mfrName, String description,
			String website, Set<Product> products) {
		this.mfrCode = mfrCode;
		this.mfrName = mfrName;
		this.description = description;
		this.website = website;
		this.products = products;
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

	@Column(name = "mfr_code", length = 45)
	public String getMfrCode() {
		return this.mfrCode;
	}

	public void setMfrCode(String mfrCode) {
		this.mfrCode = mfrCode;
	}

	@Column(name = "mfr_name", length = 45)
	public String getMfrName() {
		return this.mfrName;
	}

	public void setMfrName(String mfrName) {
		this.mfrName = mfrName;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "website", length = 45)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "manufacturer")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
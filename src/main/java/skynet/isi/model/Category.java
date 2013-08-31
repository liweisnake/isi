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
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private Category category;
	private String categoryCode;
	private String categoryName;
	private Integer childCount;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<Category> categories = new HashSet<Category>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(Category category, String categoryCode,
			String categoryName, Integer childCount, Set<Product> products,
			Set<Category> categories) {
		this.category = category;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.childCount = childCount;
		this.products = products;
		this.categories = categories;
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
	@JoinColumn(name = "parent_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "category_code", length = 45)
	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_name", length = 127)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "child_count")
	public Integer getChildCount() {
		return this.childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
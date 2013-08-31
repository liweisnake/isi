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
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resource")
public class Resource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String resourceName;
	private Set<PermissionResource> permissionResources = new HashSet<PermissionResource>(
			0);

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** full constructor */
	public Resource(String resourceName,
			Set<PermissionResource> permissionResources) {
		this.resourceName = resourceName;
		this.permissionResources = permissionResources;
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

	@Column(name = "resource_name", length = 45)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<PermissionResource> getPermissionResources() {
		return this.permissionResources;
	}

	public void setPermissionResources(
			Set<PermissionResource> permissionResources) {
		this.permissionResources = permissionResources;
	}

}
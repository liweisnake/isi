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
 * PermissionResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission_resource")
public class PermissionResource implements java.io.Serializable {

	// Fields

	private Integer id;
	private Permission permission;
	private Resource resource;

	// Constructors

	/** default constructor */
	public PermissionResource() {
	}

	/** full constructor */
	public PermissionResource(Permission permission, Resource resource) {
		this.permission = permission;
		this.resource = resource;
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
	@JoinColumn(name = "permission_id", nullable = false)
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_id", nullable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
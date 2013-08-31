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
 * Permission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission")
public class Permission implements java.io.Serializable {

	// Fields

	private Integer id;
	private String permissionCode;
	private String permissionName;
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);
	private Set<PermissionResource> permissionResources = new HashSet<PermissionResource>(
			0);

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** full constructor */
	public Permission(String permissionCode, String permissionName,
			Set<RolePermission> rolePermissions,
			Set<PermissionResource> permissionResources) {
		this.permissionCode = permissionCode;
		this.permissionName = permissionName;
		this.rolePermissions = rolePermissions;
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

	@Column(name = "permission_code", length = 45)
	public String getPermissionCode() {
		return this.permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	@Column(name = "permission_name", length = 45)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<PermissionResource> getPermissionResources() {
		return this.permissionResources;
	}

	public void setPermissionResources(
			Set<PermissionResource> permissionResources) {
		this.permissionResources = permissionResources;
	}

}
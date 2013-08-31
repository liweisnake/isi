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
 * RolePermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_permission")
public class RolePermission implements java.io.Serializable {

	// Fields

	private Integer id;
	private Permission permission;
	private Role role;

	// Constructors

	/** default constructor */
	public RolePermission() {
	}

	/** full constructor */
	public RolePermission(Permission permission, Role role) {
		this.permission = permission;
		this.role = role;
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
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
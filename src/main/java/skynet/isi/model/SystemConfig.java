package skynet.isi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_config")
public class SystemConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String configKey;
	private String configValue;
	private String description;

	// Constructors

	/** default constructor */
	public SystemConfig() {
	}

	/** full constructor */
	public SystemConfig(String configKey, String configValue, String description) {
		this.configKey = configKey;
		this.configValue = configValue;
		this.description = description;
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

	@Column(name = "config_key", length = 45)
	public String getConfigKey() {
		return this.configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	@Column(name = "config_value", length = 45)
	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Column(name = "description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
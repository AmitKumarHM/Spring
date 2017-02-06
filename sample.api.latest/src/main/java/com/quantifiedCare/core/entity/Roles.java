package com.quantifiedCare.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * The Class Roles.
 */
@XmlRootElement	
@Entity
@Table(name = "roles")
public class Roles  extends AbstractBaseEntity implements Serializable {

	/** The _logger. */
	@SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(Roles.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1358406869128693767L;

	/** The role id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long id;
	
	/** The role. */
	@Column(name = "role", nullable = true, length=100)
	private String role;
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	
}

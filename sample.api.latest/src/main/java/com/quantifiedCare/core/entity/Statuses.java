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
 * The Class Statuses.
 */
@XmlRootElement	
@Entity
@Table(name = "statuses")
public class Statuses extends AbstractBaseEntity  implements Serializable{
	
    /** The _logger. */
    @SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(Statuses.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3961857101976120835L;

	/** The status id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "status_id")
	private Long id;
	
	/** The status. */
	@Column(name = "status", nullable = true, length=100)
	private String status;
	
	
	
	/**
	 * Gets the status id.
	 *
	 * @return the status id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the status id.
	 *
	 * @param statusId the new status id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
		
}

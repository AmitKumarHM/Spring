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
 * The Class States.
 */
@XmlRootElement	
@Entity
@Table(name = "states")
public class States extends AbstractBaseEntity  implements Serializable{
	
    /** The _logger. */
    @SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(States.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 862687202207077684L;


	/** The state id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	private Long id;
	
	/** The state. */
	@Column(name = "name", nullable = true, length=100)
	private String state;
	
	/**
	 * Gets the state id.
	 *
	 * @return the state id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the state id.
	 *
	 * @param stateId the new state id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
}

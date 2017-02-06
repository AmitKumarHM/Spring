package com.quantifiedCare.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.ColumnTransformer;


/**
 * The Class CarePlans.
 */
@XmlRootElement	
@Entity
@Table(name = "care_plans")
public class CarePlans extends AbstractBaseEntity implements Serializable{
	
    /** The _logger. */
    @SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(CarePlans.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5530405810682300038L;

	/** The care plan id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "care_plan_id")
	private Long id;
	
	/** The user. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="caregiver_id")
    private Users user;
	
	/** The name. */
	@Column(name = "name", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String name;
	
	/** The patient. */
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="patient_id")
    private Users patient;
	
	/** The created date. */
	@Column(name = "created_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;	
	
	/**
	 * Gets the care plan id.
	 *
	 * @return the care plan id
	 */
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public Users getPatient() {
		return patient;
	}
	
	/**
	 * Sets the patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(Users patient) {
		this.patient = patient;
	}

	
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
		
}

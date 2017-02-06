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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ColumnTransformer;


/**
 * The Class Health.
 */
@XmlRootElement	
@Entity
@Table(name = "health")
public class Health extends AbstractBaseEntity  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The health id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "health_id")
	private Long id;
	
	/** The user. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="user_id")
    private Users user;
	
	/** The bp. */
	@Column(name = "bp", nullable = true,length=100)
	@ColumnTransformer(read = "AES_DECRYPT(bp, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String bp;
	
	/** The bmi. */
	@Column(name = "bmi", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(bmi, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String bmi; 
	
	/** The med adherence. */
	@Column(name = "medical_adherence", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(medical_adherence, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String medAdherence;
	
	/** The weight. */
	@Column(name = "weight", nullable = true,length=50)
	@ColumnTransformer(read = "AES_DECRYPT(weight, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String weight;
	
	/** The physical activity. */
	@Column(name = "physical_activity", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(physical_activity, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String physicalActivity;
	
	/** The created date. */
	@Column(name = "created_dated", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	/**
	 * Gets the health id.
	 *
	 * @return the health id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the health id.
	 *
	 * @param healthId the new health id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Gets the bp.
	 *
	 * @return the bp
	 */
	public String getBp() {
		return bp;
	}

	/**
	 * Sets the bp.
	 *
	 * @param bp the new bp
	 */
	public void setBp(String bp) {
		this.bp = bp;
	}

	/**
	 * Gets the bmi.
	 *
	 * @return the bmi
	 */
	public String getBmi() {
		return bmi;
	}

	/**
	 * Sets the bmi.
	 *
	 * @param bmi the new bmi
	 */
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	/**
	 * Gets the med adherence.
	 *
	 * @return the med adherence
	 */
	public String getMedAdherence() {
		return medAdherence;
	}

	/**
	 * Sets the med adherence.
	 *
	 * @param medAdherence the new med adherence
	 */
	public void setMedAdherence(String medAdherence) {
		this.medAdherence = medAdherence;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Gets the physical activity.
	 *
	 * @return the physical activity
	 */
	public String getPhysicalActivity() {
		return physicalActivity;
	}

	/**
	 * Sets the physical activity.
	 *
	 * @param physicalActivity the new physical activity
	 */
	public void setPhysicalActivity(String physicalActivity) {
		this.physicalActivity = physicalActivity;
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
}

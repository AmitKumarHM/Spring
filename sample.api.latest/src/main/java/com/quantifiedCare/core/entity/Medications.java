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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.ColumnTransformer;


/**
 * The Class Medications.
 */
@XmlRootElement	
@Entity
@Table(name = "medications")
public class Medications extends AbstractBaseEntity  implements Serializable {
	
    /** The _logger. */
    @SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(Medications.class);
  
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7495764372164467583L;

	/** The medication id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medication_id")
	private Long id;
	
	/** The user. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="user_id")
    private Users user;
	
	/** The medication. */
	@Column(name = "medication", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(medication, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String medication;
	
	/** The day of medication. */
	@Column(name = "day_of_medication", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(day_of_medication, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String dayOfMedication;
	
	
	/** The time of medication. */
	@Column(name = "time_of_medication", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOfMedication;
	
	/** The last filled date. */
	@Column(name = "last_filled_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastFilledDate;
	
	/** The dosage. */
	@Column(name = "dosage", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(dosage, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private Integer dosage;
	
	/** The last filled quantity. */
	@Column(name = "last_filled_quantity", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(last_filled_quantity, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private Integer lastFilledQuantity;
	
	/** The created date. */
	@Column(name = "created_dated", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * @return the medication
	 */
	public String getMedication() {
		return medication;
	}

	/**
	 * @param medication the medication to set
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}

	/**
	 * @return the dayOfMedication
	 */
	public String getDayOfMedication() {
		return dayOfMedication;
	}

	/**
	 * @param dayOfMedication the dayOfMedication to set
	 */
	public void setDayOfMedication(String dayOfMedication) {
		this.dayOfMedication = dayOfMedication;
	}

	/**
	 * @return the timeOfMedication
	 */
	public Date getTimeOfMedication() {
		return timeOfMedication;
	}

	/**
	 * @param timeOfMedication the timeOfMedication to set
	 */
	public void setTimeOfMedication(Date timeOfMedication) {
		this.timeOfMedication = timeOfMedication;
	}

	/**
	 * @return the lastFilledDate
	 */
	public Date getLastFilledDate() {
		return lastFilledDate;
	}

	/**
	 * @param lastFilledDate the lastFilledDate to set
	 */
	public void setLastFilledDate(Date lastFilledDate) {
		this.lastFilledDate = lastFilledDate;
	}

	/**
	 * @return the dosage
	 */
	public Integer getDosage() {
		return dosage;
	}

	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}

	/**
	 * @return the lastFilledQuantity
	 */
	public Integer getLastFilledQuantity() {
		return lastFilledQuantity;
	}

	/**
	 * @param lastFilledQuantity the lastFilledQuantity to set
	 */
	public void setLastFilledQuantity(Integer lastFilledQuantity) {
		this.lastFilledQuantity = lastFilledQuantity;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	
}

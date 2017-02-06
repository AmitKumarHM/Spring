package com.quantifiedCare.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.ColumnTransformer;


/**
 * The Class Organizations.
 */
@XmlRootElement	
@Entity
@Table(name = "organizations")
public class Organizations extends AbstractBaseEntity implements Serializable{

    /** The _logger. */
    @SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(Organizations.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7715277067233988831L;

	/** The org id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "org_id")
	private Long id;
	
	/** The name. */
	@Column(name = "name", nullable = true, length=100)
	@ColumnTransformer(read = "AES_DECRYPT(name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String name;
	
	
	@Transient
	private int patientCount = 0;
	
	@Transient
	private String createDate ;
	
	@Transient
	private String formatedCreatedDate;
	
	@Transient
	private int caregiverCount = 0;
	
	/** The address. */
	@Column(name = "address", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(address, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String address;
	
	/** The city. */
	@Column(name = "city", nullable = true , length=50)
	@ColumnTransformer(read = "AES_DECRYPT(city, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String city;
	
	/** The country. */
	@Column(name = "country", nullable = true , length=50)
	@ColumnTransformer(read = "AES_DECRYPT(country, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String country;
	
	/** The postal code. */
	@Column(name = "postal_code", nullable = true , length=20)
	private int postalCode;
	
	/** The contact number. */
	@Column(name = "contact_number", nullable = true , length=20)
	private String contactNumber;
	
	/** The status. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Statuses status;
	
	/** The states. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state_id")
    private States states;
	
	/** The created date. */
	@Column(name = "created_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	/** The users. */
	@OneToMany(mappedBy="organization" , fetch = FetchType.LAZY,cascade={CascadeType.ALL, CascadeType.REMOVE})
	private Set<Users> users=new HashSet<Users>(0);
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="theme_id")
    private Theme themes;
	
	
	public Theme getThemes() {
		return themes;
	}

	public void setThemes(Theme themes) {
		this.themes = themes;
	}

	public int getCaregiverCount() {
		return caregiverCount;
	}

	public void setCaregiverCount(int caregiverCount) {
		this.caregiverCount = caregiverCount;
	}

	public int getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	
	/**
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	
	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public void setFormatedCreatedDate(String formatedCreatedDate) {
		this.formatedCreatedDate = formatedCreatedDate;
	}

	/**
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Statuses getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Statuses status) {
		this.status = status;
	}
	
	/**
	 * Gets the states.
	 *
	 * @return the states
	 */
	public States getStates() {
		return states;
	}
	
	/**
	 * Sets the states.
	 *
	 * @param states the new states
	 */
	public void setStates(States states) {
		this.states = states;
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
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Set<Users> getUsers() {
		return users;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(Set<Users> users) {
		this.users = users;
	}

}

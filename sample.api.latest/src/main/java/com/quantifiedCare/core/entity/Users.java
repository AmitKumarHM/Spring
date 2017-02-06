package com.quantifiedCare.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnTransformer;


/**
 * The Class Users.
 */
@Entity
@Table(name = "users")
public class Users extends AbstractBaseEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7879209565022384685L;
	
	
	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	/** The organization. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name="organization_id")
	private Organizations organization;
	
	/** The role. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Roles role;

	/** The user name. */
	@Column(name = "user_name", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(user_name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String userName;
	
	/** The password. */
	@Column(name = "care_giver_id", nullable = false, length = 50)
	private Long careGiverId;
	
	/**
	 * @return the careGiverId
	 */
	public Long getCareGiverId() {
		return careGiverId;
	}

	/**
	 * @param careGiverId the careGiverId to set
	 */
	public void setCareGiverId(Long careGiverId) {
		this.careGiverId = careGiverId;
	}

	/** The password. */
	@Column(name = "password", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(password, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String password;
	
	/** The email id. */
	@Column(name = "email_id", nullable = true, length = 100)
	@ColumnTransformer(read = "AES_DECRYPT(email_id, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String emailId;
	
	/** The first name. */
	@Column(name = "first_name", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(first_name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String firstName;
	
	/** The middle name. */
	@Column(name = "middle_name", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(middle_name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String middleName;
	
	/** The last name. */
	@Column(name = "last_name", nullable = true, length = 100)
	@ColumnTransformer(read = "AES_DECRYPT(last_name, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String lastName;
	
	/** The address. */
	@Column(name = "address", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(address, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String address;
	
	/** The height. */
	@Column(name = "height", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(height, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String height;
	
	/** The weight. */
	@Column(name = "weight", nullable = true, length = 20)
	@ColumnTransformer(read = "AES_DECRYPT(weight, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String weight;
	
	/** The blood group. */
	@Column(name = "blood_group", nullable = true, length = 20)
	@ColumnTransformer(read = "AES_DECRYPT(blood_group, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String bloodGroup;
	
	/** The medical condition. */
	@Column(name = "medical_condition", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(medical_condition, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String medicalCondition;
	
	/** The compilance. */
	@Column(name = "compilance", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(compilance, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String compilance;
	
	/** The postal code. */
	@Column(name = "postal_code", nullable = true, length = 20)
	private Integer postalCode;
	
	/** The mobile number. */
	@Column(name = "mobile_number", nullable = false , length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(mobile_number, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String mobileNumber;
	
	/** The home number. */
	@Column(name = "home_number", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(home_number, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String homeNumber;
	
	/** The office number. */
	@Column(name = "office_number", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(office_number, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String officeNumber;
	
	/** The forget token. */
	@Column(name = "forget_token", nullable = true)
	private String forgetToken;
	
	/** The age. */
	@Column(name = "age", nullable = true)
	private Short age;
	
	/** The country. */
	@Column(name = "country", nullable = true, length = 50)
	@ColumnTransformer(read = "AES_DECRYPT(country, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String country;
	
	/** The date of birth. */
	@Column(name = "dob", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	/** The last contact date. */
	@Column(name = "last_contact_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastContactDate;
	
	/** The created date. */
	@Column(name = "created_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** The memberId */
	@Column(name = "memberId", nullable = true, length = 50)
	private String memberId;
	
	/** gender. */
	@Column(name = "gender", nullable = true, length = 50)
	private String gender;
	
	
 	
	/** The medications. */
	@OneToMany(mappedBy="user" , fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	private Set<Medications> medications=new HashSet<Medications>(0);
   
	/** The messages. */
	@OneToMany(mappedBy="fromUser" , fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	private Set<Messages> messages=new HashSet<Messages>(0);
	
	/** The to messages. */
	@OneToMany(mappedBy="toUser" , fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	private Set<Messages> toMessages=new HashSet<Messages>(0);
   
	/** The symptoms. */
	@OneToMany(mappedBy="user" , fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	private Set<Symptoms> symptoms=new HashSet<Symptoms>(0);
	
	/** The access tokens. */
	@OneToMany(mappedBy="user" , fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	private Set<AccessTokens> accessTokens=new HashSet<AccessTokens>(0);
  
    /** The status. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status_id")
    private Statuses status;
	
    /** The state. */
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="state_id")
    private States state;
    
   
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	
	public Long getUserId() {
		return id;
	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	
	public Organizations getOrganization() {
		return organization;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	
	public Roles getRole() {
		return role;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	
	public String getLastName() {
		return lastName;
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
	 * Gets the state.
	 *
	 * @return the state
	 */
	
	public States getState() {
		return state;
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
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	
	public Integer getPostalCode() {
		return postalCode;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	
	public Short getAge() {
		return age;
	}
	
	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * Gets the mobile number.
	 *
	 * @return the mobile number
	 */
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Gets the office number.
	 *
	 * @return the office number
	 */
	
	public String getOfficeNumber() {
		return officeNumber;
	}
	
	/**
	 * Gets the home number.
	 *
	 * @return the home number
	 */
	
	public String getHomeNumber() {
		return homeNumber;
	}

	/**
	 * Gets the forget token.
	 *
	 * @return the forget token
	 */
	@Column(name = "forget_token", nullable = true)
	public String getForgetToken() {
		return forgetToken;
	}
	
	/**
	 * Gets the blood group.
	 *
	 * @return the blood group
	 */
	
	public String getBloodGroup() {
		return bloodGroup;
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
	 * Gets the height.
	 *
	 * @return the height
	 */
	
	public String getHeight() {
		return height;
	}

	/**
	 * Gets the medical condition.
	 *
	 * @return the medical condition
	 */
	
	public String getMedicalCondition() {
		return medicalCondition;
	}

	/**
	 * Gets the compilance.
	 *
	 * @return the compilance
	 */
	
	public String getCompilance() {
		return compilance;
	}

	/**
	 * Gets the last contact date.
	 *
	 * @return the last contact date
	 */
	
	public Date getLastContactDate() {
		return lastContactDate;
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
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	
	public Date getCreatedDate() {
		return createdDate;
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
	 * Gets the medications.
	 *
	 * @return the medications
	 */
	
	public Set<Medications> getMedications() {
		return medications;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	
	public Set<Messages> getMessages() {
		return messages;
	}

	/**
	 * Gets the to messages.
	 *
	 * @return the to messages
	 */
	
	public Set<Messages> getToMessages() {
		return toMessages;
	}

	
	/**
	 * Gets the symptoms.
	 *
	 * @return the symptoms
	 */
	
	public Set<Symptoms> getSymptoms() {
		return symptoms;
	}

	/**
	 * Gets the access tokens.
	 *
	 * @return the access tokens
	 */
	
	public Set<AccessTokens> getAccessTokens() {
		return accessTokens;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organizations organization) {
		this.organization = organization;
	}
	
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(String height) {
		this.height = height;
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
	 * Sets the blood group.
	 *
	 * @param bloodGroup the new blood group
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Sets the medical condition.
	 *
	 * @param medicalCondition the new medical condition
	 */
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	/**
	 * Sets the compilance.
	 *
	 * @param compilance the new compilance
	 */
	public void setCompilance(String compilance) {
		this.compilance = compilance;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Sets the mobile number.
	 *
	 * @param mobileNumber the new mobile number
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Sets the home number.
	 *
	 * @param homeNumber the new home number
	 */
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	/**
	 * Sets the office number.
	 *
	 * @param officeNumber the new office number
	 */
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(Short age) {
		this.age = age;
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
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Sets the last contact date.
	 *
	 * @param lastContactDate the new last contact date
	 */
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
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
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	

	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}

	/**
	 * Sets the symptoms.
	 *
	 * @param symptoms the new symptoms
	 */
	public void setSymptoms(Set<Symptoms> symptoms) {
		this.symptoms = symptoms;
	}

	/**
	 * Sets the access tokens.
	 *
	 * @param accessTokens the new access tokens
	 */
	public void setAccessTokens(Set<AccessTokens> accessTokens) {
		this.accessTokens = accessTokens;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Roles role) {
		this.role = role;
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
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(States state) {
		this.state = state;
	}

	
	/**
	 * Sets the to messages.
	 *
	 * @param toMessages the new to messages
	 */
	public void setToMessages(Set<Messages> toMessages) {
		this.toMessages = toMessages;
	}	
	
	/**
	 * Sets the forget token.
	 *
	 * @param forgetToken the new forget token
	 */
	public void setForgetToken(String forgetToken) {
		this.forgetToken = forgetToken;
	}

	
	public Long getId() {
	return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public String generatePass() {
		int len = 8;
		Random randomInstance = new Random();
		char[] choices = ("abcdefghjklmnpqrstuvwxyz"
				+ "ABCDEFGHJKLMNPQRSTUVWXYZ" + "123456789" + "#$&")
				.toCharArray();
		StringBuilder salt = new StringBuilder(len);
		for (int i = 0; i < len; ++i)
			salt.append(choices[randomInstance.nextInt(choices.length)]);
		return salt.toString();
	}
}

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
 * The Class Messages.
 */
@XmlRootElement	
@Entity
@Table(name = "messages")
public class Messages extends AbstractBaseEntity  implements Serializable{

	/** The _logger. */
	@SuppressWarnings("unused")
	private static Log _logger = LogFactory.getLog(Messages.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -67484760191452657L;

	/** The message id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private Long id;
	
	/** The subject. */
	@Column(name = "subject", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(subject, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String subject;
	
	/** The body. */
	@Column(name = "body", nullable = true)
	@ColumnTransformer(read = "AES_DECRYPT(body, 'kEyLI1Fy648tzWXGuRcxrg==')", write = "AES_ENCRYPT(?, 'kEyLI1Fy648tzWXGuRcxrg==')")
	private String body;

		
	/** The from user. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="fromUser")
    private Users fromUser;	
	
	/** The to user. */
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="toUser")
    private Users toUser;
	
	/** The updated date. */
	@Column(name = "updated_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	/** The created date. */
	@Column(name = "created_date", nullable = true, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}


	/**
	 * @return the fromUser
	 */
	public Users getFromUser() {
		return fromUser;
	}

	/**
	 * @param fromUser the fromUser to set
	 */
	public void setFromUser(Users fromUser) {
		this.fromUser = fromUser;
	}

	/**
	 * @return the toUser
	 */
	public Users getToUser() {
		return toUser;
	}

	/**
	 * @param toUser the toUser to set
	 */
	public void setToUser(Users toUser) {
		this.toUser = toUser;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

package com.bluepiit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement	
@Entity
@Table(name = "User")
@NamedQueries({@NamedQuery(name=User.GET_USERS_LIST,query="select u from User u"),
	           @NamedQuery(name=User.GET_USER_BY_ID,query="select u from User u where u.userId=:userId")})
public class User implements Serializable{
	private static final long serialVersionUID = -1661527419693036326L;
	public static final String GET_USERS_LIST = "User.getUserList";
	public static final String GET_USER_BY_ID = "User.getUserById";
	private String userName;
	private String password;
	private String emailId;
	private int userId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}

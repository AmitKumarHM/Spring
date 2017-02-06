/**
 * 
 */
package com.quantifiedCare.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.jpa.GenericJPARepository;

/**
 * @author arvind
 *
 */
public interface UserRepository  extends GenericJPARepository<Users>{
	
	
	@Query("select users from Users users where users.emailId  = :emailId")
	Users getUserByUserName(@Param("emailId") String emailId );
	
	@Query("SELECT users FROM Users AS users")
	List<Users> getRolesName();
	
	@Query("SELECT users FROM Users AS users")
	Users getRoleName();
	
	@Modifying  
	@Transactional
	@Query("UPDATE Users users SET users.forgetToken = :forgetToken WHERE users.emailId = :emailId")
	void updateForgotTokenByEmailId(@Param("forgetToken") String forgetToken,@Param("emailId") String emailId);
	
	
	@Query("SELECT users from Users users WHERE users.id  = :id")
	Users getUserByUserId(@Param("id") Long id );
	
	
	@Modifying  
	@Transactional
	@Query("UPDATE Users users SET users.password = :password WHERE users.id = :id")
	void resetPasswordByPassword(@Param("password") String password,@Param("id") Long  id);
	
	@Query("select roles from Roles roles where roles.id  = :id")
	public Roles getRoleById(@Param("id") Long id);
	
	@Query("SELECT states FROM States AS states")
	List<States> getStates();
	/*
	@Query("select users from Users users where users.emailId  = :emailId AND users.password  = :password")
	Users getUserByUserNameAndPassword(@Param("emailId") String emailId,@Param("password") String password );
	*/
	
	
	
	
	/*@Query("select user from User user where user.email = :email")
	User getUserByEmailID(@Param("email") String email);*/
	
	/*@Query("select locker_keys.skeylabel from User locker_keys ORDER BY locker_keys ASC")
	List<User> getUserLabelByName();*/
	
	/*@Query("select locker_keys.skeylabel from User locker_keys where locker_keys.lname = :lname")
	List<User> getUserLabelByName(@Param("lname")String lname);
	
	
	@Query("select locker_keys from User locker_keys where locker_keys.skey  = :skey")
	List<User> getAllUsersByKey(@Param("skey") String skey );*/
	
	
	@Query("SELECT users FROM Users users where users.organization.id = :org_id AND users.role.id  = :role_id")
	Users retriveUsersByOrganizationId(@Param("org_id") Long org_id,@Param("role_id") Long role_id);
	
}






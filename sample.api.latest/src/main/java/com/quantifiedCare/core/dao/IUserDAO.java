/**
 * 
 */
package com.quantifiedCare.core.dao;

import java.util.List;

import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;

/**
 * @author arvind
 *
 */
public interface IUserDAO extends IGenericDAO<Users>{
	
	 /**
	 * @param emailId
	 * @return
	 */
	Users getUserByUserName(String emailId);
	 
	 /**
	 * @return
	 */
	List<Users> getRolesName();
	 
	 Users getRoleName();
	 
	 /**
	 * @param forgetToken
	 * @param emailId
	 */
	void updateForgotTokenByEmailId(String forgetToken,String emailId);
	 
	 /**
	 * @param id
	 * @return
	 */
	Users getUserByUserId(Long id);
	 
	 /**
	 * @param password
	 * @param id
	 */
	void resetPasswordByPassword(String password, Long id);
	 
	 /**
	 * @param id
	 * @return
	 */
	public Roles getRoleById(Long id);
	 
	 /**
	 * @return
	 */
	List<States> getStates();
	 
	 /**
	 * @param org_id
	 * @param role_id
	 * @return
	 */
	Users retriveUsersByOrganizationId(Long org_id,Long role_id);
	 
	/* Users getUserByUserNameAndPassword(String emailId,String password );*/
	 
}

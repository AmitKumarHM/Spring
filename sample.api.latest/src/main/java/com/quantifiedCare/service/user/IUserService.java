/**
 * 
 */
package com.quantifiedCare.service.user;

import java.util.List;

import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.service.IGenericService;


public interface IUserService extends IGenericService<Users>{
	public Roles getRoleById(Long id);
	
	public Users getUserByUserName(String emailId);
	
	List<Users> getRolesName();
	
	Users getRoleName();
	
	 void updateForgotTokenByEmailId(String forgetToken,String emailId);
	 
	 Users getUserByUserId(Long id);
	 
	 void resetPasswordByPassword(String password,Long id);
	 
	 List<States> getStates();
	 
	 Users retriveUsersByOrganizationId(Long org_id,Long role_id);
	 

	
	
	
	
}

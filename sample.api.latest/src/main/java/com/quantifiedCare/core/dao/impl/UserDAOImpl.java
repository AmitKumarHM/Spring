/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IUserDAO;
import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.repository.UserRepository;

/**
 * @author arvind
 *
 */
@Repository
public class UserDAOImpl extends AbstractDAO<Users> implements IUserDAO {
	
	
	/** The user repository. */
	@Autowired
	public UserRepository userRepository;
	
	
	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.userRepository);
	}


	public Users getUserByUserName(String emailId) {
		Users users = userRepository.getUserByUserName(emailId);
		return users;
	}


	public List<Users> getRolesName() {
		List<Users> usersSet = userRepository.getRolesName();
		return usersSet;
	}


	public Users getRoleName() {
		Users users = userRepository.getRoleName();
		return users;
	}
	
	

	public void updateForgotTokenByEmailId(String forgetToken,String emailId) {
		userRepository.updateForgotTokenByEmailId(forgetToken,emailId);
		
	}


	public void resetPasswordByPassword(String password, Long id) {
		userRepository.resetPasswordByPassword(password, id);
		
	}


	public Users getUserByUserId(Long id) {
		Users users = userRepository.getUserByUserId(id);
		return users;
	}


	public Roles getRoleById(Long id) {
		Roles roles= userRepository.getRoleById(id);
		return roles;
	}
	

	public List<States> getStates() {
		List<States> states= userRepository.getStates();
		return states;
	}


	public Users retriveUsersByOrganizationId(Long org_id,Long role_id) {
		Users users= userRepository.retriveUsersByOrganizationId(org_id,role_id);
		return users;
	}
	
}

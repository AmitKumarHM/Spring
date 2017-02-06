
package com.quantifiedCare.service.userimpl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantifiedCare.core.dao.IUserDAO;
import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.service.AbstractService;
import com.quantifiedCare.service.user.IUserService;

/**
 * @author arvind
 *
 */
@Service
public class UserServiceImpl extends AbstractService<Users> implements IUserService{
	
	
	@Autowired
	public IUserDAO userDAO;
	
	
	@Override
	@PostConstruct
	protected void setGenericDAO() {
		super.setBaseDAO(userDAO);
		
	}
	
	@Override
	public Users getUserByUserName(String emailId) {
		Users users = userDAO.getUserByUserName(emailId);
		return users;
	}

	@Override
	public List<Users> getRolesName() {
		List<Users>  usersSet = userDAO.getRolesName();
		return usersSet;
	}

	@Override
	public Users getRoleName() {
		Users  users = userDAO.getRoleName();
		return users;
	}

	

	@Override
	public void updateForgotTokenByEmailId(String forgetToken,String emailId) {
		userDAO.updateForgotTokenByEmailId(forgetToken,emailId);
		
	}

	@Override
	public void resetPasswordByPassword(String password, Long id) {
		userDAO.resetPasswordByPassword(password, id);
		
	}

	@Override
	public Users getUserByUserId(Long id) {
		Users users = userDAO.getUserByUserId(id);
		return users;
	}

	@Override
	public Roles getRoleById(Long id) {
		Roles  roles = userDAO.getRoleById(id);
		return roles;
	}
	
	@Override
	public List<States> getStates() {
		List<States> states = userDAO.getStates();
		return states;
	}

	@Override
	public Users retriveUsersByOrganizationId(Long org_id,Long role_id) {
		Users users = userDAO.retriveUsersByOrganizationId( org_id,role_id);
		return users;
	}
}

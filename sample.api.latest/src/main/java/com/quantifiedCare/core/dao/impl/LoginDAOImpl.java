/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.ILoginDAO;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.repository.LoginRepository;

/**
 * @author arvind
 *
 */
@Repository
public class LoginDAOImpl extends AbstractDAO<Users> implements ILoginDAO {
	
	/** The login repository. */
	@Autowired
	public LoginRepository loginRepository;

	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.loginRepository);
	}
	
	
}

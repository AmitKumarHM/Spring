/**
 * 
 */
package com.quantifiedCare.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantifiedCare.core.dao.ILoginDAO;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.service.AbstractService;
import com.quantifiedCare.service.ILoginService;

/**
 * @author arvind
 *
 */
@Service
public class LoginServiceImpl extends AbstractService<Users>implements ILoginService{
	
	@Autowired
	public ILoginDAO loginDAO;

	@Override
	@PostConstruct
	protected void setGenericDAO() {
		super.setBaseDAO(loginDAO);

	}
}

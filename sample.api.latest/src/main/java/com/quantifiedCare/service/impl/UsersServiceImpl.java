package com.quantifiedCare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantifiedCare.core.dao.IUsersDAO;
import com.quantifiedCare.core.entity.User;
import com.quantifiedCare.service.AbstractService;
import com.quantifiedCare.service.IUsersService;

@Service
public class UsersServiceImpl extends AbstractService<User>implements IUsersService{

	@Autowired
	public IUsersDAO usersDAO;
	
	@Override
	protected void setGenericDAO() {	
		super.setBaseDAO(usersDAO);
	}

}

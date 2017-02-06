package com.quantifiedCare.core.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IUsersDAO;
import com.quantifiedCare.core.entity.User;
import com.quantifiedCare.core.repository.UsersRepository;

@Repository
public class UsersDAOImpl extends AbstractDAO<User> implements IUsersDAO{

	@Autowired
	public UsersRepository usersRepository;
	
	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.usersRepository);
		
	}
	
	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
}

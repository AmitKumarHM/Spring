package com.bluepiit.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluepiit.model.User;
import com.bluepiit.service.UserService;
import com.bluepiit.service.internal.doa.UserDao;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
	private UserDao userDao;
	public List<User> getUserList() {
		return userDao.findUserList();
	}
	public User getUser(int id) {		
		return userDao.findUser(id);
	}

}

package com.bluepiit.service.internal.doa.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bluepiit.model.User;
import com.bluepiit.service.internal.doa.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<User> findUserList() {	
		Query userList = entityManager.createNamedQuery(User.GET_USERS_LIST);
		@SuppressWarnings("unchecked")
		List<User> objUserList =(List<User>)userList.getResultList();
		return objUserList;
	}

	public User findUser(int id) {
		Query userList = entityManager.createNamedQuery(User.GET_USER_BY_ID);
		userList.setParameter("userId", id);
		@SuppressWarnings("unchecked")
		List<User> objUserList = (List<User>)userList.getResultList();
		return objUserList.get(0);
	}
}

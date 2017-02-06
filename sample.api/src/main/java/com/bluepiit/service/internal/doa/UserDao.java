package com.bluepiit.service.internal.doa;

import java.util.List;

import com.bluepiit.model.User;

public interface UserDao {

List<User> findUserList(); 
User findUser(int id);
}

package com.bluepiit.service;

import java.util.List;

import com.bluepiit.model.User;

public interface UserService {

List<User> getUserList(); 
User getUser(int id);
}

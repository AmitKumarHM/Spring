package com.quantifiedCare.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantifiedCare.core.entity.User;
import com.quantifiedCare.service.IUsersService;
 

@Controller
@RequestMapping(value="user")
public class UserServiceApi {

	@Autowired
    private IUsersService usersService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") long id) {
		return usersService.getEntityById(id);		
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public List<User> getUserlist() {
		return usersService.loadAllEntityData();
	}
	
	@RequestMapping("/test/{param}")
	@ResponseBody
	public String getMsg(@PathVariable("param") String msg) {
        String output = "Jersey say : " + msg;
        return output;
     }
	
}

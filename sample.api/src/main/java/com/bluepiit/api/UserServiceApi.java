package com.bluepiit.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluepiit.model.User;
import com.bluepiit.service.UserService;
 
@Path("/user")
@Component
public class UserServiceApi {

	@Autowired
    private UserService	userService;
	
	@GET
	@Path("/{id}")
	@Produces({"application/json"})
	public User getUser(@PathParam("id") int id) {
		return userService.getUser(id);		
	}
	
	@GET
	@Path("/all")
	@Produces({"application/json"})
	public List<User> getUserlist() {
		System.out.println(userService);
		return userService.getUserList();
	}
	
	@GET
	@Path("/test/{param}")
	public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
     }
	
}

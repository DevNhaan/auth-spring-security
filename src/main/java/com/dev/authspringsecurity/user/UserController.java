package com.dev.authspringsecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
    private UserService service;
    @PostMapping
    public Object create(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/{user-id}/{role-id}")
    public Object addRole(@PathVariable(name="user-id") String userId,
                          @PathVariable(name="role-id") String roleId){
    	boolean isAdded= service.addRole(userId, roleId);
    	if(!isAdded)
    		return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    	return  new ResponseEntity<>("success", HttpStatus.OK);
    }
}

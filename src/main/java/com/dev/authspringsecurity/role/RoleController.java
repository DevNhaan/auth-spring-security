package com.dev.authspringsecurity.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    private RoleService service;
    @PostMapping
    public Object createRole(@RequestBody Role role){
        Role res = service.save(role);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }
    @GetMapping
    public Object findAllRole() {
    	return new ResponseEntity<Object>(service.findAll(), HttpStatus.OK);
    }
}

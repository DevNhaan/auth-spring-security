package com.dev.authspringsecurity.security.auth;

import com.dev.authspringsecurity.security.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO dto){
        UserResponseDTO res = service.login(dto);
        if(res == null)
            return new ResponseEntity<>("Username or password is valid.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public Object register(@RequestBody LoginDTO dto){
        boolean isSuccess = service.register(dto);
        if(!isSuccess)
            return new ResponseEntity<Object>("Register failure.", HttpStatus.OK);

        return new ResponseEntity<Object>("Register successful.Login now.", HttpStatus.OK);
    }
}

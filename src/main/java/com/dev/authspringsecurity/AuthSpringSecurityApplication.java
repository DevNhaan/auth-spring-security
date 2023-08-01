package com.dev.authspringsecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dev.authspringsecurity.role.Role;
import com.dev.authspringsecurity.role.RoleService;
import com.dev.authspringsecurity.user.SUser;
import com.dev.authspringsecurity.user.UserService;

@SpringBootApplication
public class AuthSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthSpringSecurityApplication.class, args);
        System.out.println("Hibernate Version: "+ org.hibernate.Version.getVersionString());
    }
    @Bean
    CommandLineRunner run(UserService userService,RoleService roleService) {
    	return args -> {
//    		Role role1 = roleService.save(Role.builder().name("ROLE_ADMIN").build());
//    		Role role2 = roleService.save(Role.builder().name("ROLE_USER").build());
//    		
//    		SUser user1 = userService.save(SUser.builder().username("admin").password("1234").build());
//    		SUser user2 = userService.save(SUser.builder().username("user").password("1234").build());
//    		
//    		userService.addRole(user1.getId(), role1.getId());
//    		userService.addRole(user2.getId(), role2.getId());
    	};
    	
    }

}

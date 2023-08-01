package com.dev.authspringsecurity.security.dto;

import java.util.Set;

import com.dev.authspringsecurity.role.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class UserResponseDTO {
	private String id;
    private String username;
    private Set<Role> role;
    private String token;
}

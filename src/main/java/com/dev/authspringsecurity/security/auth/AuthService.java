package com.dev.authspringsecurity.security.auth;

import com.dev.authspringsecurity.security.dto.UserResponseDTO;

public interface AuthService {
    UserResponseDTO login(LoginDTO dto);

    boolean register(LoginDTO dto);
}

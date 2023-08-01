package com.dev.authspringsecurity.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.authspringsecurity.role.Role;
import com.dev.authspringsecurity.security.dto.UserResponseDTO;
import com.dev.authspringsecurity.security.jwts.JwtHelper;
import com.dev.authspringsecurity.user.SUser;
import com.dev.authspringsecurity.user.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public UserResponseDTO login(LoginDTO dto) {
        Optional<SUser> userOpt = userRepository.findByUsername(dto.getUsername());
        if(userOpt.isEmpty())
            return null;
        SUser user = userOpt.get();
        String token = null;

        if(passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            Set<Role> roles = user.getRoles();
            if(roles != null){
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                roles.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
                token = jwtHelper.generateJwt(user,authorities);
            }
        }


        if(token == null) return null;

        return UserResponseDTO.builder()
                                .token(token)
                                .id(user.getId().toString())
                                .username(user.getUsername())
                                .role(user.getRoles())
                                .build();
    }

    @Override
    public boolean register(LoginDTO dto) {
        try{
            SUser user = SUser.builder()
                                .username(dto.getUsername())
                                .password(passwordEncoder.encode(dto.getPassword()))
                                .build();
            userRepository.save(user);
            
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

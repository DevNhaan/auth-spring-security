package com.dev.authspringsecurity.security.dto;

import com.dev.authspringsecurity.user.SUser;
import com.dev.authspringsecurity.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SUser> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty())
            return null;
        SUser user = userOpt.get();

        return new User(user.getUsername(),user.getPassword(),getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(SUser user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        user.getRoles().stream().forEach(item -> authorities.add(new SimpleGrantedAuthority(item.getName())));

        return authorities;
    }
}

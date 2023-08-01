package com.dev.authspringsecurity.security.jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenrizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService service;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = helper.getToken(request);
        try{
            if(token != null){
                String username = helper.getUsername(token);
                UserDetails userDetails = service.loadUserByUsername(username);
                Authentication auth = new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request,response);
            }
        }catch (Exception ex){
            response.setHeader("Error",ex.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        filterChain.doFilter(request,response);
    }
}

package com.dev.authspringsecurity.security.jwts;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dev.authspringsecurity.user.SUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtHelper {
    private static final String SECRET_KEY = "jwtsecretkey";

    public String generateJwt(SUser user, Collection<SimpleGrantedAuthority> authorities){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50* 60 * 1000))
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
            if(jwt == null)
                return null;
            return jwt.substring("Bearer ".length(),jwt.length());
    }
    public boolean validateAccessToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes())).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return true;
        } catch (Exception ex1) {
            System.out.println("Jwt is not supported: " + ex1);
        }
        return false;
    }
    
    public String getUsername(String token) {
    	JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes())).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getSubject();
    }

}

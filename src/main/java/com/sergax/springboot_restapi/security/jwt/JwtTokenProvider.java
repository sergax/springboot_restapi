package com.sergax.springboot_restapi.security.jwt;

import com.sergax.springboot_restapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long validityInMilSeconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @PostConstruct
    protected  void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

//    public String createToken(String login, List<Role> roles) {
//        Claims claims = Jwts.claims().setSubject(login);
//        claims.put("roles", getRoleNames(roles));
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()//
//                .setClaims(claims)//
//                .setIssuedAt(now)//
//                .setExpiration(validity)//
//                .signWith(SignatureAlgorithm.HS256, secret)//
//                .compact();
//    }
//
//    public Authentication getAuthentication(String token) {
//
//    }
//
//    public String getLogin(String token) {
//
//    }
//
//    public boolean ValidateToken(String token) {
//
//    }

    private List<String> getRoleNames(List<Role> userRole) {
        List<String> result = new ArrayList<>();
        userRole.forEach(role -> result.add(role.getLogin()));
        return result;
    }
}

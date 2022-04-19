package com.sergax.springboot_restapi.controller;


import com.sergax.springboot_restapi.dto.AuthenticationRequestDto;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.security.jwt.JwtTokenProvider;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationControllerV1 {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserServise userServise;

    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
            User user = userServise.findByLogin(login);

            if (user == null) {
                throw new UsernameNotFoundException("Not found login");
            }
            String token = jwtTokenProvider.createToken(login, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid login or password");
        }
    }
}

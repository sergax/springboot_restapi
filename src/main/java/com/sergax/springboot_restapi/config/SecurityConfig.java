package com.sergax.springboot_restapi.config;

import com.sergax.springboot_restapi.security.jwt.JwtConfiguration;
import com.sergax.springboot_restapi.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */
/*
{
    "login":"serg",
    "password":"$2a$12$Br824VtL/p0jFCs5BLxek.C74.T4XDMIPF6FDtVqRMEZvGzBh2xoy",
    "firstName":"Sergey",
    "lastName":"Sergeev",
    "email":"s.sergeev@mail.ru"
}
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**"; //$2a$12$Qi6jaxizr7BA1IqIV9Llt.9O0T8HoBf2Vooz1OynIM7DW7HHunVd2
    private static final String MODER_ENDPOINT = "/api/v1/moder/**"; //$2a$12$iTfQwUzjUvwrE5EiB8oiJe/zWbDBPSwAsYU1B2TXWi92XAcQrKbSG
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login"; //$2a$12$2qZR.SBkexktbWcHMghEkOfbMggrw/wk5O1yWTzoCj/XLI.64C68a

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(MODER_ENDPOINT).hasRole("MODER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfiguration(jwtTokenProvider));
    }
}

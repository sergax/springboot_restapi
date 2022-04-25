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

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**"; //$2a$12$x6qqOiuEbTw2SpnC58j.o.aIQbny4ymEj4om6n3Qta/HP0pXJ4w9e
    private static final String MODER_ENDPOINT = "/api/v1/moder/**"; //$2a$12$XtUnAPvoCCFm3ojB/Ex7AOZfq3pV4DgNXEJAVg7W5tQ6Yf3KtDNBy
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

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

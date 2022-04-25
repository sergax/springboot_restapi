package com.sergax.springboot_restapi.security;

import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.security.jwt.JwtUser;
import com.sergax.springboot_restapi.security.jwt.JwtUserFactory;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Component
@Slf4j
public class JwtUserDetailService implements UserDetailsService {
    private final UserServise userServise;

    @Autowired
    public JwtUserDetailService(UserServise userServise) {
        this.userServise = userServise;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userServise.findByLogin(login);
        if (user == null) {
            log.error("User not found in Database");
            throw new UsernameNotFoundException("Not found User by Login : " + login);
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("User with login : {} was loaded", login);
        return jwtUser;
    }
}

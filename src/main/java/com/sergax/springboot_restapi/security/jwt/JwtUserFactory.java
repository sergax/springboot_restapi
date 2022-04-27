package com.sergax.springboot_restapi.security.jwt;

import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@NoArgsConstructor
public final class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}

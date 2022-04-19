package com.sergax.springboot_restapi.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

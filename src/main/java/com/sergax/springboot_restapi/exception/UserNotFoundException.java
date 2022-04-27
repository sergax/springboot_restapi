package com.sergax.springboot_restapi.exception;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Couldn't find user by this id : " + id);
    }

    public UserNotFoundException(String login) {
        super("Couldn't find user by this Login : " + login);
    }
}

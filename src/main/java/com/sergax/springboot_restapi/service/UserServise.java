package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.User;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface UserServise extends GenericService<User, Long> {
    User findByLogin(String login);
}

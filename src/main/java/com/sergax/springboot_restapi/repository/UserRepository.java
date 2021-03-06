package com.sergax.springboot_restapi.repository;

import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    User findUserById(Long id);
}

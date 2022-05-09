package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.RoleRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findUserById(id);

        log.info("User by ID : {}", user);
        return UserDto.fromUser(user);
    }

    @Override
    public User createUser(User user) {
        User newUser = userRepository.save(user);

        log.info("Created new User : {}", newUser);
        return newUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> allUsers() {
        List<User> listUsers = userRepository.findAll();
        List<UserDto> users = listUsers.stream().map(UserDto::fromUser).collect(Collectors.toList());

        log.info("All Users : {}", listUsers);
        return users;
    }

    @Override
    public List<Role> allRoles() {
        List<Role> roleList = roleRepository.findAll();

        log.info("All Roles : {}", roleList);
        return roleList;
    }

    @Override
    public User updateUser(Long userId, User user) {
        User updatedUser = userRepository.findById(userId)
                .map(u -> {
                    u.setLogin(user.getLogin());
                    u.setFirstName(user.getFirstName());
                    u.setLastName(user.getLastName());
                    return userRepository.save(u);
                })
                .orElseGet(() -> {
                    user.setId(userId);
                    return userRepository.save(user);
                });

        log.info("User by ID : {}, was updated", userId);
        return updatedUser;
    }
}

package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.model.Event;
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
    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);

        log.info("User by ID : {}", user);
        return user;
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
    public List<User> allUsers() {
        List<User> listUsers = userRepository.findAll();

        log.info("All Users : {}", listUsers);
        return listUsers;
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getById(eventId);

        log.info("Event by ID : {}", event);
        return event;
    }

    @Override
    public List<Event> allEvents() {
        List<Event> eventList = eventRepository.findAll();

        log.info("All Events : {}", eventList);
        return eventList;
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

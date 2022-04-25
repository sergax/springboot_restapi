package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserServise {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("All Users : {}", users.size());
        return users;
    }

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        log.info("Found User : {} by Login : {}", user, login);
        return user;
    }

    @Override
    public List<Event> allEvents() {
        List<Event> eventList = eventRepository.findAll();
        log.info("All Events : {}", eventList);
        return eventList;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        log.info("Found User : {} by Id : {}", user, id);
        return user;
    }

    @Override
    public User create(User user) {
        user.setLogin(user.getLogin());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstNAme(user.getFirstNAme());
        user.setLastNAme(user.getLastNAme());
        user.setEmail(user.getEmail());
        user.setEvents(user.getEvents());
        User newUser = userRepository.save(user);
        log.info("Created New User : {}", newUser);
        return newUser;
    }

    @Override
    public User update(User user) {
        User updatedUser = userRepository.save(user);
        log.info("Updated User : {}", updatedUser);
        return updatedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User by Id : {} was deleted", id);
    }
}

package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.RoleRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private final FileRepository fileRepository;
    private final RoleRepository roleRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(BCrypt.gensalt(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void setRoleForUser(Long userId, String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) throw new RoleInfoNotFoundException("Couldn't found Role by name : " + roleName);

        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);
    }

    @Override
    public void setEventForUser(Long userId, Long eventId) {
        Event event = eventRepository.findEventById(eventId);
        if (event == null) throw new EventNotFoundException("Couldn't found File by ID : " + eventId);

        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.getById(eventId);
    }

    @Override
    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<File> allFiles() {
        return fileRepository.findAll();
    }
}

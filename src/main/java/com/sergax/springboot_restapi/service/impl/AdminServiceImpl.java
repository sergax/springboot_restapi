package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.dto.UserDto;
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
    private final FileRepository fileRepository;
    private final RoleRepository roleRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
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

    @Override
    public void setRole(Long userId, Long roleId) {
        User user = userRepository.findUserById(userId);
        Role role = roleRepository.findRoleById(roleId);

        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}

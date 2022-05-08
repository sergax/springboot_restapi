package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;

import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

public interface AdminService {
    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long id);

    List<User> allUsers();

    Event getEventById(Long eventId);

    List<Event> allEvents();

    List<Role> allRoles();
}

package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.User;

import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

public interface AdminService {
    User getUserById(Long id);

    User createUser(User user);

    void deleteUser(Long id);

    List<User> allUsers();

    Event getEventById(Long eventId);

    List<Event> allEvents();

    void upload(Long userId, String bucket, String path);

    void setFile(Long userId, Long fileId);

    void deleteFileById(Long id);

}

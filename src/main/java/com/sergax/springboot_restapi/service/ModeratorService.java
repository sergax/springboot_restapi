package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;

import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */
public interface ModeratorService {

    void setFileFoUser(Long userId, Long fileId);

    void setUserForRole(Long userId, Long roleId);

    File uploadFile(File file);

    List<File> allFiles();

    void createEvent(User user, File file, String eventName);
}

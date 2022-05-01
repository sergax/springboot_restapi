package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;

import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */
public interface ModeratorService {
    File upload(Long userId, String location);

    File createFile(File file);

    void setFile(Long userId, Long fileId);

    void deleteFileById(Long id);

    List<File> allFiles();

    List<Event> allEvents();
}

package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;


import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface UserServise {
    User getByLogin(String login);

    FileDto getFileById(Long id);

    List<FileDto> allFiles();

    EventDto getEventById(Long id);

    List<EventDto> allEvents();
}

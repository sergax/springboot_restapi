package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;


import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface UserServise {
    File getFileById(Long id);
    List<File> allFiles();

    Event getEventById(Long id);
    List<Event> allEvents();
}

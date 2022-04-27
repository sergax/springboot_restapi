package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserServise {
    private final EventRepository eventRepository;
    private final FileRepository fileRepository;


    @SneakyThrows
    @Override
    public File getFileById(Long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File doesn't exist by id : " + id));

        log.info("File : {} ; by id : {}", file, id);
        return file;
    }

    @SneakyThrows
    @Override
    public List<File> allFiles() {
        List<File> fileList = fileRepository.findAll();
        if (fileList == null) throw new FileNotFoundException("Empty List of Files :(");

        log.info("All Files : {}", fileList);
        return fileList;
    }

    @Override
    public Event getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        log.info("Event : {} ; by id : {}", event, id);
        return null;
    }

    @Override
    public List<Event> allEvents() {
        List<Event> eventList = eventRepository.findAll();
        if (eventList == null) throw new EventNotFoundException("Empty List of Events :(");

        log.info("All Events : {}", eventList);
        return eventList;
    }
}

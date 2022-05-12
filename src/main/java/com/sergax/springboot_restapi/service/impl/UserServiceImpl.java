package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserServise {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final FileRepository fileRepository;

    @Override
    public User getByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) throw new UserNotFoundException(login);

        log.info("User : {} ; by Login : {}", user, login);
        return user;
    }

    @SneakyThrows
    @Override
    public FileDto getFileById(Long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File doesn't exist by id : " + id));

        log.info("File : {} ; by id : {}", file, id);
        return FileDto.fromFile(file);
    }

    @SneakyThrows
    @Override
    public List<FileDto> allFiles() {
        List<File> fileList = fileRepository.findAll();
        List<FileDto> fileDtoList = fileList.stream().map(FileDto::fromFile).collect(Collectors.toList());
        if (fileList == null) throw new FileNotFoundException("Wasn't found any Files 😑");

        log.info("All Files : {}", fileList);
        return fileDtoList;
    }

    @Override
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        log.info("Event : {} ; by id : {}", event, id);
        return EventDto.fromEvent(event);
    }

    @Override
    public List<EventDto> allEvents() {
        List<Event> eventList = eventRepository.findAll();
        List<EventDto> eventDtoList = eventList.stream().map(EventDto::fromEvent).collect(Collectors.toList());

        log.info("All Events : {}", eventList);
        return eventDtoList;
    }
}

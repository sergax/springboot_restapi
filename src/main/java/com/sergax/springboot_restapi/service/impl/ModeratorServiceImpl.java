package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.RoleRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.AWSBucketService.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RoleRepository roleRepository;
    private final BucketService bucketService;


    @SneakyThrows
    @Override
    public void setFileFoUser(Long userId, Long fileId) {
        File file = fileRepository.getById(fileId);
        if (file == null) throw new FileNotFoundException("Couldn't found File by ID : " + fileId);

        User user = userRepository.findUserById(userId);
        if (user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);

        createEvent(user, file,
                "Set File ID : " + file.getId() +
                        " to User ID : " + userId);
    }

    @SneakyThrows
    @Override
    public File uploadFile(File file) {
        bucketService.putObject(file);

        file.setFileName(file.getFileName());
        file.setLocation(file.getLocation());
        file.setLastModified(LocalDateTime.now());
        file.setType(Files.probeContentType(Paths.get(file.getLocation())));
        file.setSize((float) Files.size(Paths.get(file.getLocation())));
        fileRepository.save(file);

        return file;
    }

    @Override
    public void createEvent(User user, File file, String eventName) {
        Event event = new Event();
        event.setEventName(eventName);
        event.setFiles(file);
        event.setUsers(user);
        eventRepository.save(event);
    }

    @Override
    public void setUserForRole(Long userId, Long roleId) {
        User user = userRepository.findUserById(userId);
        Role role = roleRepository.getById(roleId);
        List<Role> rolesList = new ArrayList<>();
        rolesList.add(role);
        user.setRoles(rolesList);

        userRepository.save(user);
        roleRepository.save(role);
    }
}

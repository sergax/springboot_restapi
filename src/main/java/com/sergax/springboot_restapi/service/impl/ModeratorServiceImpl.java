package com.sergax.springboot_restapi.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.AWSService;
import com.sergax.springboot_restapi.service.EventService;
import com.sergax.springboot_restapi.service.ModeratorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final AWSService awsService;

    @Override
    public File upload(Long userId, String bucket, String path) {
        S3Object s3Object = awsService.upload(bucket, path);

        File file = new File();
        file.setFileName(s3Object.getKey());
        file.setLocation(file.getLocation());
        fileRepository.save(file);

        log.info("File : {} was uploaded", file);

        User user = userRepository.findUserById(userId);

        eventService.createEvent(user, file,
                "Uploaded File ID : " + file.getId() +
                        " to User ID : " + userId);
        return file;
    }

    @SneakyThrows
    @Override
    public void setFile(Long userId, Long fileId) {
        File file = fileRepository.getById(fileId);
        if (file == null) throw new FileNotFoundException("Couldn't found File by ID : " + fileId);

        User user = userRepository.findUserById(userId);
        if(user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);

        eventService.createEvent(user, file,
                "Set File ID : " + file.getId() +
                        " to User ID : " + userId);
    }

    @Override
    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }
}

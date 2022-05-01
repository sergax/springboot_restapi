package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.config.AWSClientConfig;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.properties.AWSS3Properties;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.AWSService;
import com.sergax.springboot_restapi.service.EventService;
import com.sergax.springboot_restapi.service.ModeratorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
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
    private final EventService eventService;
    private final EventRepository eventRepository;
    private final AWSService awsService;
    private final AWSClientConfig awsClientConfig;
    private final AWSS3Properties properties;
//    private final AmazonS3 amazonS3;

    //    @Override
    public File upload(Long userID, String location) {
//        S3Object s3Object = awsService.upload(bucketName, location);
//
//        File file = new File();
//        file.setFileName(s3Object.getKey());
//        file.setLocation(s3Object.getBucketName());
//
//        User user = userRepository.findUserById(userID);
//
//        eventService.createEvent(user, file, "Set File ID : " + file.getId() +
//                " to User ID : " + user.getId());
//
//        log.info("File : {} was uploaded", file.getFileName());
//
//        return file;
        return new File();
    }

    @SneakyThrows
    @Override
    public void setFile(Long userId, Long fileId) {
        File file = fileRepository.getById(fileId);
        if (file == null) throw new FileNotFoundException("Couldn't found File by ID : " + fileId);

        User user = userRepository.findUserById(userId);
        if (user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);

        eventService.createEvent(user, file,
                "Set File ID : " + file.getId() +
                        " to User ID : " + userId);
    }

    @Override
    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File createFile(File file) {
        File newFile = fileRepository.save(file);
        log.info("File was created : {}", file);

        return newFile;
    }

    @Override
    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<File> allFiles() {
        return fileRepository.findAll();
    }
}

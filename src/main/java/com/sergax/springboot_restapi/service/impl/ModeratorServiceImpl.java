package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.UserRepository;
import com.sergax.springboot_restapi.service.EventService;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
    private final BucketService bucketService;


    @SneakyThrows
    @Override
    public void setFileFoUser(Long userId, Long fileId) {
        File file = fileRepository.getById(fileId);
        if (file == null) throw new FileNotFoundException("Couldn't found File by ID : " + fileId);

        User user = userRepository.findUserById(userId);
        if (user == null) throw new UserNotFoundException("Couldn't found User by ID : " + userId);

        eventService.createEvent(user, file,
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

    @SneakyThrows
    @Override
    public List<File> allFiles() {
        List<File> fileList = fileRepository.findAll();
        if(fileList == null) throw new FileNotFoundException("Wasn't found any Files 😑");
        log.info("All Files : {}", fileList);

        return fileList;
    }
}

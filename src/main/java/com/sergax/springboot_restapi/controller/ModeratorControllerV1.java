package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/moderator/")
@PreAuthorize("hasAnyRole('MODER','ADMIN')")
@RequiredArgsConstructor
public class ModeratorControllerV1 {
    private final ModeratorService moderatorService;
    private final BucketService bucketService;

    @PostMapping("/files/set/{userId}/{fileId}")
    public ResponseEntity<?> setFileForUser(@PathVariable Long userId,
                                            @PathVariable Long fileId) {
        moderatorService.setFile(userId, fileId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @PutMapping("/files/upload/{userId}/{bucketName}/{location}")
//    public ResponseEntity<?> uploadFile(@PathVariable Long userId,
//                                        @PathVariable String bucketName,
//                                        @PathVariable String location) {
//        File file = moderatorService.upload(userId, bucketName, location);
//
//        return ResponseEntity.ok("File uploaded : " + file);
//    }

    @PutMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestBody File file) {
        try {
            bucketService.putObject(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("File uploaded" + file.getFileName());
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long fileId) {
        moderatorService.deleteFileById(fileId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/files/create")
    public ResponseEntity<?> createFile(@RequestBody File file) {
        moderatorService.createFile(file);

        return ResponseEntity.ok(file);
    }

    @GetMapping("/files")
    public ResponseEntity<?> allFiles() {
        List<File> fileList = moderatorService.allFiles();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/events")
    public ResponseEntity<?> allEvents() {
        List<Event> events = moderatorService.allEvents();
        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDto> eventDtoList = events.stream().map(EventDto::fromEvent).collect(Collectors.toList());
        return ResponseEntity.ok(eventDtoList);
    }
}

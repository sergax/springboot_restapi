package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.UserServise;
import com.sergax.springboot_restapi.service.AWSBucketService.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;


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
    private final UserServise userServise;
    private final BucketService bucketService;

    @PostMapping("/files/set/{userId}/{fileId}")
    public ResponseEntity<?> setFileForUser(@PathVariable Long userId,
                                            @PathVariable Long fileId) {
        moderatorService.setFileFoUser(userId, fileId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestBody File file) {
        moderatorService.uploadFile(file);

        return ResponseEntity.ok("File uploaded : " + file);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        bucketService.deleteObject(fileName);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/files")
    public ResponseEntity<?> allFiles() {
        List<File> fileList = moderatorService.allFiles();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/files/AWSBucketService")
    public ResponseEntity<?> allFilesInBucket() {
        ListObjectsResponse fileList = bucketService.listBucketContent();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(fileList.contents().stream().map(S3Object::toString));
    }

    @GetMapping("/events")
    public ResponseEntity<?> allEvents() {
        List<Event> events = userServise.allEvents();
        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDto> eventDtoList = events.stream().map(EventDto::fromEvent).collect(Collectors.toList());
        return ResponseEntity.ok(eventDtoList);
    }
}

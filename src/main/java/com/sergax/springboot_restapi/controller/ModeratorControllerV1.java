package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.assembler.EventModelAssembler;
import com.sergax.springboot_restapi.model.assembler.FileModelAssembler;
import com.sergax.springboot_restapi.service.AdminService;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.UserServise;
import com.sergax.springboot_restapi.service.AWSBucketService.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/moderator/")
@RequiredArgsConstructor
public class ModeratorControllerV1 {
    private final ModeratorService moderatorService;
    private final AdminService adminService;
    private final BucketService bucketService;
    private final FileModelAssembler fileModelAssembler;
    private final EventModelAssembler eventModelAssembler;

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

    @DeleteMapping("/files/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        bucketService.deleteObject(fileName);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/files")
    public CollectionModel<EntityModel<FileDto>> allFiles() {
        List<EntityModel<FileDto>> fileList = moderatorService.allFiles().stream()
                .map(fileModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(fileList, linkTo(methodOn(ModeratorControllerV1.class).allFiles()).withSelfRel());
    }

    @GetMapping("/files/buckets")
    public ResponseEntity<?> allFilesInBucket() {
        ListObjectsResponse fileList = bucketService.listBucketContent();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(fileList.contents().stream().map(S3Object::toString));
    }

    @GetMapping("/events")
    public CollectionModel<EntityModel<EventDto>> allEvents() {
        List<EntityModel<EventDto>> events = adminService.allEvents().stream()
                .map(eventModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(events, linkTo(methodOn(ModeratorControllerV1.class).allEvents()).withSelfRel());
    }

    @PutMapping("/users/roles/{userId}/{roleId}")
    public ResponseEntity<?> setUserForRole(@PathVariable Long userId,
                                            @PathVariable Long roleId) {
        moderatorService.setUserForRole(userId, roleId);

        return ResponseEntity.noContent().build();
    }
}

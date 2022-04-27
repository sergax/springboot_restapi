package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/moderator/")
@PreAuthorize("hasAnyRole('MODER','ADMIN')")
@RequiredArgsConstructor
public class ModeratorControllerV1 {
    private final ModeratorService moderatorService;

    @PostMapping("/files/set/{userId}/{fileId}")
    public ResponseEntity<?> setFileForUser(@PathVariable Long userId,
                                            @PathVariable Long fileId) {
        moderatorService.setFile(userId, fileId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/files/upload/{userId}/{bucket}/{filePath}")
    public ResponseEntity<?> uploadFile(@PathVariable Long userId,
                                        @PathVariable String bucket,
                                        @PathVariable String path) {
        File file = moderatorService.upload(userId, bucket, path);

        return ResponseEntity.ok(file);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long fileId) {
        moderatorService.deleteFileById(fileId);

        return ResponseEntity.noContent().build();
    }
}

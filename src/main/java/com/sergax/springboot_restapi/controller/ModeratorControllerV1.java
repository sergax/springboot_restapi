package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.service.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/moderator/")
@PreAuthorize("hasAnyRole('MODER_ROLE','ADMIN')")
@RequiredArgsConstructor
public class ModeratorControllerV1 {
    private final ModeratorService moderatorService;

    @PostMapping("/files/{userId}/{fileId}")
    public ResponseEntity<Void> setFileForUser(@PathVariable Long userId,
                                               @PathVariable Long fileId) {
        moderatorService.setFile(userId, fileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

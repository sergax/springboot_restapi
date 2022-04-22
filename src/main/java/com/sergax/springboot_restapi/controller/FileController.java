package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * by Aksenchenko Serhii on 22.04.2022
 */

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private FileService fileService;

    @SneakyThrows
    @GetMapping
    ResponseEntity<?> all() {
        return ResponseEntity.ok(fileService.fileList()
                .orElseThrow(() -> new FileNotFoundException("Files doesn't exist")));
    }

    @PostMapping
    ResponseEntity<?> upload(@RequestBody FileDto fileDto) {
        fileService.upload(fileDto.toEntity());

        return ResponseEntity.ok("File uploaded");
    }
}

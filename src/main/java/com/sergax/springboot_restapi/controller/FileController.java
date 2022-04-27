package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.service.AWSService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * by Aksenchenko Serhii on 22.04.2022
 */

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private AWSService fileService;

}

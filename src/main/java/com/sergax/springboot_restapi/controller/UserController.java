package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserServise userServise;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userServise.getAll());
    }
}

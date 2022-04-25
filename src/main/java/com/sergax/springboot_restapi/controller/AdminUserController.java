package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserServise userServise;

    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        List<User> user = userServise.getAll();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> userDtoList = user.stream().map(UserDto::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> oneUserById(@PathVariable Long id) {
        User user = userServise.findById(id);
        if (id == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(UserDto.fromUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userServise.delete(id);

        return ResponseEntity.noContent().build();
    }
}

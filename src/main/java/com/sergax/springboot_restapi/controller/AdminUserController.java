package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.service.AdminService;
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
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        List<User> user = adminService.allUsers();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> userDtoList = user.stream().map(UserDto::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/events")
    public ResponseEntity<?> allEvents() {
        List<Event> events = adminService.allEvents();
        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDto> eventDtoList = events.stream().map(EventDto::fromEvent).collect(Collectors.toList());
        return ResponseEntity.ok(eventDtoList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> oneUserById(@PathVariable Long id) {
        User user = adminService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(UserDto.fromUser(user));
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> oneEventById(@PathVariable Long id) {
        Event event = adminService.getEventById(id);
        if (event == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(EventDto.fromEvent(event));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/set/{userId}/{fileId}")
    public ResponseEntity<Void> setFileToUser(@PathVariable Long userId,
                                              @PathVariable Long fileId) {
        return null;
    }
}

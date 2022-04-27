package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.repository.FileRepository;
import com.sergax.springboot_restapi.repository.RoleRepository;
import com.sergax.springboot_restapi.service.AdminService;
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
public class AdminUserControllerV1 {
    private final AdminService adminService;
    private final RoleRepository roleRepository;
    private final EventRepository eventRepository;
    private final ModeratorControllerV1 moderatorControllerV1;
    private final FileRepository fileRepository;

    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        List<User> user = adminService.allUsers();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> userDtoList = user.stream().map(UserDto::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> oneUserById(@PathVariable Long id) {
        User user = adminService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(UserDto.fromUser(user));
    }

    @PutMapping("/users/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = adminService.createUser(userDto);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/set/{userId}/{roleName}")
    public ResponseEntity<?> setRoleForUser(@PathVariable Long userId,
                                            @PathVariable String roleName) {
        adminService.setRoleForUser(userId, roleName);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/users/set/{userID}/{eventId}")
    public ResponseEntity<?> setEventForUser(@PathVariable Long userId,
                                             @PathVariable Long eventId) {
        adminService.setEventForUser(userId, eventId);

        return ResponseEntity.ok(HttpStatus.OK);
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

    @GetMapping("/events/{id}")
    public ResponseEntity<?> oneEventById(@PathVariable Long id) {
        Event event = adminService.getEventById(id);
        if (event == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(EventDto.fromEvent(event));
    }

    @PostMapping("/files/set/{userId}/{fileId}")
    public ResponseEntity<?> setFileToUser(@PathVariable Long userId,
                                           @PathVariable Long fileId) {
        moderatorControllerV1.setFileForUser(userId, fileId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/files/upload/{userId}/{bucket}/{filePath}")
    public ResponseEntity<?> uploadFile(@PathVariable Long userId,
                                        @PathVariable String bucket,
                                        @PathVariable String filePath) {
        moderatorControllerV1.uploadFile(userId, bucket, filePath);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/files/delete/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable Long id) {
        moderatorControllerV1.deleteFile(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/files")
    public ResponseEntity<?> allFiles() {
        List<File> fileList = fileRepository.findAll();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(fileList);
    }
}

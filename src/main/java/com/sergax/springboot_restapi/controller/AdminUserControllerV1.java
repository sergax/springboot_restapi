package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.dto.RoleDto;
import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.exception.UserNotFoundException;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.model.assembler.EventModelAssembler;
import com.sergax.springboot_restapi.model.assembler.FileModelAssembler;
import com.sergax.springboot_restapi.model.assembler.UserModelAssembler;
import com.sergax.springboot_restapi.service.AdminService;
import com.sergax.springboot_restapi.service.ModeratorService;
import com.sergax.springboot_restapi.service.AWSBucketService.BucketService;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminUserControllerV1 {
    private final AdminService adminService;
    private final UserServise userServise;
    private final ModeratorService moderatorService;
    private final BucketService bucketService;
    private final FileModelAssembler fileModelAssembler;
    private final UserModelAssembler userModelAssembler;
    private final EventModelAssembler eventModelAssembler;

    @GetMapping("/users")
    public CollectionModel<EntityModel<UserDto>> allUsers() {
        List<EntityModel<UserDto>> users = adminService.allUsers().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(AdminUserControllerV1.class).allUsers()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<UserDto> oneUserById(@PathVariable Long id) {
        UserDto user = adminService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return userModelAssembler.toModel(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User newUser = adminService.createUser(user);

        return ResponseEntity.ok(UserDto.fromUser(newUser));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                        @PathVariable Long userId) {
        User updatedUser = adminService.updateUser(userId, user);

        return ResponseEntity.ok(UserDto.fromUser(updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @PutMapping("/users/roles/{userId}/{roleId}")
    public ResponseEntity<?> setUserForRole(@PathVariable Long userId,
                                            @PathVariable Long roleId) {
        if (userId == null) {
            throw new UserNotFoundException("Couldn't find user by ID : " + userId);
        }
        if (roleId == null) {
            throw new RoleNotFoundException("Couldn't find role by ID : " + roleId);
        }
        moderatorService.setUserForRole(userId, roleId);

        return ResponseEntity.ok("User by ID : " + userId + " with Role ID : " + roleId + " was established");
    }

    @GetMapping("/roles")
    public ResponseEntity<?> allRoles() {
        List<Role> roles = adminService.allRoles();
        if (roles == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<RoleDto> roleDtos = roles.stream().map(RoleDto::fromRole).collect(Collectors.toList());
        return ResponseEntity.ok(roleDtos);
    }

    @GetMapping("/events")
    public CollectionModel<EntityModel<EventDto>> allEvents() {
        List<EntityModel<EventDto>> events = userServise.allEvents().stream()
                .map(eventModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(events,
                linkTo(methodOn(ModeratorControllerV1.class).allEvents()).withSelfRel());
    }

    @GetMapping("/events/{id}")
    public EntityModel<EventDto> getEventById(@PathVariable Long id) {
        EventDto event = userServise.getEventById(id);
        if (event == null) throw new EventNotFoundException(id);

        return eventModelAssembler.toModel(event);
    }

    @PostMapping("/users/files/{userId}/{fileId}")
    public ResponseEntity<?> setFileToUser(@PathVariable Long userId,
                                           @PathVariable Long fileId) {
        moderatorService.setFileFoUser(userId, fileId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestBody File file) {
        moderatorService.uploadFile(file);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/files/{fileName}")
    public ResponseEntity<?> deleteFileById(@PathVariable String fileName) {
        bucketService.deleteObject(fileName);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/files")
    public CollectionModel<EntityModel<FileDto>> allFiles() {
        List<EntityModel<FileDto>> fileList = userServise.allFiles().stream()
                .map(fileModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(fileList,
                linkTo(methodOn(ModeratorControllerV1.class).allFiles()).withSelfRel());
    }

    @SneakyThrows
    @GetMapping("/files/{id}")
    public EntityModel<FileDto> getFileById(@PathVariable Long id) {
        FileDto file = userServise.getFileById(id);
        if (file == null) throw new FileNotFoundException();

        return fileModelAssembler.toModel(file);
    }
}

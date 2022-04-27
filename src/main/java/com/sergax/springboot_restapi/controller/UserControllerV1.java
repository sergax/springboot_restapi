package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerV1 {
    private final UserServise userServise;

    @GetMapping("/files/{id}")
    public ResponseEntity<?> getFileById(@PathVariable Long id) {
        File file = userServise.getFileById(id);
        if (file == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(file);
    }

    @GetMapping("/files")
    public ResponseEntity<?> allFiles() {
        List<File> fileList = userServise.allFiles();
        if (fileList == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Event event = userServise.getEventById(id);
        if (event == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(EventDto.fromEvent(event));
    }

    @GetMapping("/events")
    public ResponseEntity<?> allEvents() {
        List<Event> eventList = userServise.allEvents();
        if (eventList == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<EventDto> eventDtoList = eventList.stream().map(EventDto::fromEvent).collect(Collectors.toList());
        return ResponseEntity.ok(eventDtoList);
    }
}

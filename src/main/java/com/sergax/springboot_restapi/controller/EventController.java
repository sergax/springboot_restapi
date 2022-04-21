package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.UserDto;
import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * by Aksenchenko Serhii on 20.04.2022
 */

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getAll() {
        List<Event> events = eventService.getAll();
        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDto> eventDtoList = events.stream().map(EventDto::fromEvent).collect(Collectors.toList());
        return ResponseEntity.ok(eventDtoList);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        if (event == null) {
            throw new EventNotFoundException(id);
        }
        return ResponseEntity.ok(EventDto.fromEvent(event));
    }

    @PostMapping("/events")
    ResponseEntity<?> newEvent(@RequestBody Event event) {
        Event newEvent = eventService.create(event);

        return ResponseEntity.ok(newEvent);
    }
}

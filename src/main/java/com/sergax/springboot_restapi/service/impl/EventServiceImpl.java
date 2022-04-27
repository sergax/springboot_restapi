package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.service.EventService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public void createEvent(User user, File file, String eventName) {
        Event event = new Event();
        event.setEventName(eventName);
        event.setFiles(file);
        event.setUsers(user);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEventsByUserId(Long userId) {
        return eventRepository.getEventByUserId(userId);
    }
}

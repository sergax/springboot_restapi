package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.repository.EventRepository;
import com.sergax.springboot_restapi.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAll() {
        List<Event> events = eventRepository.findAll();
        log.info("All Events : {}", events.size());
        return events;
    }

    @Override
    public Event findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        log.info("Found Event : {} by Id : {}", event, id);
        return event;
    }

    @Override
    public Event create(Event event) {
        Event newEvent = eventRepository.save(event);
        log.info("Created New Event : {}", newEvent);
        return newEvent;
    }

    @Override
    public Event update(Event event) {
        Event updatedEvent = eventRepository.save(event);
        log.info("Updated Event : {}", updatedEvent);
        return updatedEvent;
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
        log.info("Event by Id: {} was deleted", id);
    }
}

package com.sergax.springboot_restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class EventDto {
    private Long id;
    private String eventName;
    private String firstName;
    private String lastName;
    private String fileName;

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setEventName(event.getEventName());
        eventDto.setFirstName(event.getUsers().getFirstNAme());
        eventDto.setLastName(event.getUsers().getLastNAme());
        eventDto.setFileName(event.getFiles().getFileName());
        return eventDto;
    }

    public static List<EventDto> fromListEvents(List<Event> events) {
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event : events) {
            eventDtos.add(fromEvent(event));
        }
        return eventDtos;
    }
}

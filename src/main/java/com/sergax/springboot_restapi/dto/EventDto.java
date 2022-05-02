package com.sergax.springboot_restapi.dto;

import com.sergax.springboot_restapi.model.Event;
import lombok.Data;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class EventDto {
    private Long id;
    private Long userId;
    private String eventName;
    private String firstName;
    private String lastName;

    private FileDto fileDto;

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setUserId(event.getUsers().getId());
        eventDto.setEventName(event.getEventName());
        eventDto.setFirstName(event.getUsers().getFirstName());
        eventDto.setLastName(event.getUsers().getLastName());

        eventDto.setFileDto(FileDto.fromFile(event.getFiles()));

        return eventDto;
    }
}

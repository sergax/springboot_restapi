package com.sergax.springboot_restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Long fileId;
    private String fileName;

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setUserId(event.getUsers().getId());
        eventDto.setEventName(event.getEventName());
        eventDto.setFirstName(event.getUsers().getFirstName());
        eventDto.setLastName(event.getUsers().getLastName());
        eventDto.setFileId(event.getFiles().getId());
        eventDto.setFileName(event.getFiles().getFileName());
        return eventDto;
    }
}

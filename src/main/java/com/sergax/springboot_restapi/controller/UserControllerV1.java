package com.sergax.springboot_restapi.controller;

import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.exception.EventNotFoundException;
import com.sergax.springboot_restapi.model.assembler.EventModelAssembler;
import com.sergax.springboot_restapi.model.assembler.FileModelAssembler;
import com.sergax.springboot_restapi.service.UserServise;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */

@RestController
@RequestMapping("/api/v1/users/")
@RequiredArgsConstructor
public class UserControllerV1 {
    private final UserServise userServise;
    private final FileModelAssembler fileModelAssembler;
    private final EventModelAssembler eventModelAssembler;

    @SneakyThrows
    @GetMapping("/files/{id}")
    public EntityModel<FileDto> getFileById(@PathVariable Long id) {
        FileDto file = userServise.getFileById(id);
        if (file == null) throw new FileNotFoundException();

        return fileModelAssembler.toModel(file);
    }

    @GetMapping("/files")
    public CollectionModel<EntityModel<FileDto>> allFiles() {
        List<EntityModel<FileDto>> fileList = userServise.allFiles().stream()
                .map(fileModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(fileList, linkTo(methodOn(ModeratorControllerV1.class).allFiles()).withSelfRel());
    }

    @GetMapping("/events/{id}")
    public EntityModel<EventDto> getEventById(@PathVariable Long id) {
        EventDto event = userServise.getEventById(id);
        if (event == null) throw new EventNotFoundException(id);

        return eventModelAssembler.toModel(event);
    }

    @GetMapping("/events")
    public CollectionModel<EntityModel<EventDto>> allEvents() {
        List<EntityModel<EventDto>> events = userServise.allEvents().stream()
                .map(eventModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(events, linkTo(methodOn(ModeratorControllerV1.class).allEvents()).withSelfRel());
    }
}

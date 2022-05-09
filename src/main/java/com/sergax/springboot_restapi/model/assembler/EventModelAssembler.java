package com.sergax.springboot_restapi.model.assembler;

import com.sergax.springboot_restapi.controller.ModeratorControllerV1;
import com.sergax.springboot_restapi.dto.EventDto;
import com.sergax.springboot_restapi.model.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * by Aksenchenko Serhii on 09.05.2022
 */

@Component
public class EventModelAssembler implements RepresentationModelAssembler<EventDto, EntityModel<EventDto>> {

    @Override
    public EntityModel<EventDto> toModel(EventDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ModeratorControllerV1.class).allEvents()).withRel("Events"));
    }
}

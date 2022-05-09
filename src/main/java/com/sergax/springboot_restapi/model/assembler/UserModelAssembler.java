package com.sergax.springboot_restapi.model.assembler;

import com.sergax.springboot_restapi.controller.AdminUserControllerV1;
import com.sergax.springboot_restapi.dto.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * by Aksenchenko Serhii on 09.05.2022
 */

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    @Override
    public EntityModel<UserDto> toModel(UserDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AdminUserControllerV1.class).oneUserById(entity.getId())).withSelfRel(),
                linkTo(methodOn(AdminUserControllerV1.class).allUsers()).withRel("Users"));
    }
}

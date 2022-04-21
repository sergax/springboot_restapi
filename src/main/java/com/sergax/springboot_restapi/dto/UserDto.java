package com.sergax.springboot_restapi.dto;

import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class UserDto {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleDto> roles;
    private List<EventDto> events;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setFirstName(user.getFirstNAme());
        userDto.setLastName(user.getLastNAme());
        userDto.setEmail(user.getEmail());

        List<RoleDto> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(RoleDto.fromRole(role));
        }

        List<EventDto> events = new ArrayList<>();
        for (Event event : user.getEvents()) {
            events.add(EventDto.fromEvent(event));
        }

        userDto.setRoles(roles);
        userDto.setEvents(events);
        return userDto;
    }

    public static List<UserDto> fromListUsers(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(fromUser(user));
        }
        return userDtos;
    }
}
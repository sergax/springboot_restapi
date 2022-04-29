package com.sergax.springboot_restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergax.springboot_restapi.model.Role;
import com.sergax.springboot_restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * by Aksenchenko Serhii on 20.04.2022
 */

@Data
public class RoleDto {
    private Long id;
    private String roleName;

    public static RoleDto fromRole(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}

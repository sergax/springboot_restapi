package com.sergax.springboot_restapi.dto;

import com.sergax.springboot_restapi.model.Role;
import lombok.Data;

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

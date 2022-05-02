package com.sergax.springboot_restapi.repository;

import com.sergax.springboot_restapi.dto.RoleDto;
import com.sergax.springboot_restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}

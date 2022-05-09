package com.sergax.springboot_restapi.repository;

import com.sergax.springboot_restapi.dto.FileDto;
import com.sergax.springboot_restapi.model.File;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

public interface FileRepository extends JpaRepository<File, Long> {
}

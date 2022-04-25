package com.sergax.springboot_restapi.repository;

import com.sergax.springboot_restapi.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

public interface FileRepository extends JpaRepository<File, Long> {
}

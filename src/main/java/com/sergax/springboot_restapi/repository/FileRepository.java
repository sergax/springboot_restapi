package com.sergax.springboot_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

public interface FileRepository extends JpaRepository<File, Long> {
}

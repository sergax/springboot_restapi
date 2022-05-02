package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.File;

import java.util.List;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */
public interface ModeratorService {

    void setFileFoUser(Long userId, Long fileId);

    File uploadFile(File file);

    List<File> allFiles();
}

package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.File;

/**
 * by Aksenchenko Serhii on 27.04.2022
 */
public interface ModeratorService {
    void upload(Long userId, String bucket, String path);

    void setFile(Long userId, Long fileId);

    void deleteFileById(Long id);
}

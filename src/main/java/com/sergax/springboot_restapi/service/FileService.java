package com.sergax.springboot_restapi.service;

import com.sergax.springboot_restapi.model.File;

import java.util.Optional;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface FileService {

    Object gimmeClient();

    void upload(File file);

    void download(File file);

    Optional<String> fileList();

    void delete(String fileName);
}

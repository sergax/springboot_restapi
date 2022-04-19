package com.sergax.springboot_restapi.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {
//    private final AmazonS3 amazonS3;
//    private final S3Properties s3Properties;

    @Override
    public void upload(File file) {

    }

    @Override
    public InputStream download(File file) {
        return null;
    }

    @Override
    public Optional<String> fileList() {
        return Optional.empty();
    }

    @Override
    public void delete(String fileName) {

    }
}

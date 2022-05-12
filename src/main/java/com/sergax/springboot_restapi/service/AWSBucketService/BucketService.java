package com.sergax.springboot_restapi.service.AWSBucketService;

import com.sergax.springboot_restapi.config.S3ClientConfig;
import com.sergax.springboot_restapi.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

/**
 * by Aksenchenko Serhii on 01.05.2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BucketService {
    private final S3ClientConfig config;

    public ListObjectsResponse listBucketContent() {
        S3Client s3Client = config.gimmeClient();
        ListObjectsResponse response = s3Client.listObjects(ListObjectsRequest
                .builder()
                .bucket(config.getBucketName())
                .build());

        response.contents().forEach(c ->
                log.info("Bucket object: {}", c.key()));

        return response;
    }

    public void deleteObject(String fileName) {
        S3Client s3Client = config.gimmeClient();
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(config.getBucketName())
                .key(fileName)
                .build());

        s3Client.close();
    }

    public void putObject(File file) throws IOException {
        S3Client s3Client = config.gimmeClient();
        java.io.File newFile = new java.io.File(file.getLocation());
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(config.getBucketName())
                        .key(file.getFileName())
                        .build(),
                RequestBody.fromFile(newFile));

        s3Client.close();
    }
}

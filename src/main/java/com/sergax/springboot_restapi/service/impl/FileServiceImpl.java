package com.sergax.springboot_restapi.service.impl;

import com.sergax.springboot_restapi.config.AWSBucketConfig;
import com.sergax.springboot_restapi.model.File;
import com.sergax.springboot_restapi.service.FileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private AWSBucketConfig config;
    private S3Client s3Client;

    @Override
    public S3Client gimmeClient() {

        Region region = Region.EU_CENTRAL_1;
        return S3Client.builder()
                .region(region)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        config.getAwsId(),
                                        config.getAwsKey()
                                )
                        )
                )
                .build();
    }

    @SneakyThrows
    @Override
    public void upload(File file) {
        InputStream resourceAsStream = UserServiceImpl.class
                .getResourceAsStream(file.getFileName());
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(config.getBucketName())
                        .key(file.getFileName())
                        .build(),
                RequestBody.fromInputStream(resourceAsStream, resourceAsStream.available())
        );
    }

    @SneakyThrows
    @Override
    public void download(File file) {
        InputStream resourceAsStream = FileServiceImpl.class
                .getResourceAsStream(file.getFileName());
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(config.getBucketName())
                        .key(file.getFileName())
                        .build(),
                RequestBody.fromInputStream(resourceAsStream, resourceAsStream.available())
        );
    }

    @SneakyThrows
    @Override
    public Optional<String> fileList() {
        S3Client s3Client = gimmeClient();
        ListObjectsResponse objects = s3Client.listObjects(
                ListObjectsRequest.builder()
                        .bucket(config.getBucketName())
                        .build()
        );
        objects.contents().forEach(System.out::println);

        if (objects == null) throw new FileNotFoundException("Files Storage is Empty :( ");
        s3Client.close();

        return Optional.of(String.valueOf(objects));
    }

    @Override
    public void delete(String fileName) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(config.getBucketName())
                .key(fileName)
                .build());
    }
}


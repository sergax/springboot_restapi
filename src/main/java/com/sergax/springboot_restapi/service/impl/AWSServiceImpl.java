package com.sergax.springboot_restapi.service.impl;


import com.sergax.springboot_restapi.config.AWSClientConfig;
import com.sergax.springboot_restapi.service.AWSService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */

@Service
@Slf4j
@AllArgsConstructor
public class AWSServiceImpl implements AWSService {
//    private final AmazonS3 amazonS3;
//
//    @Override
//    public S3Object upload(String bucketName, String location) {
//        File file = new File(location);
//
//        PutObjectResult putObjectResult = amazonS3.putObject(
//                bucketName,
//                file.getName(),
//                file);
//        return getS3Object(bucketName, file.getName());
//    }
//
//    @Override
//    public ObjectListing fileList(String bucketName) {
//        return amazonS3.listObjects(bucketName);
//    }
//
//    @Override
//    public S3Object getS3Object(String bucketName, String key) {
//        return amazonS3.getObject(bucketName, key);
//    }
}


package com.sergax.springboot_restapi.service.bucket;

import com.sergax.springboot_restapi.config.DemoConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * by Aksenchenko Serhii on 01.05.2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BucketService {
    private final DemoConfig config;

    public S3Client gimmeClient() {

        Region region = Region.EU_CENTRAL_1;
        return S3Client.builder()
                .region(region)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        config.getAccessId(),
                                        config.getSecretKey()
                                )
                        )
                )
                .build();
    }


    public void createListDeleteObject(String fileName) throws IOException {
        S3Client s3Client = gimmeClient();
        String remotefilename = "/" + fileName
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss"))
                + ".txt";
        putObject();

        ListObjectsResponse objects = s3Client.listObjects(
                ListObjectsRequest.builder()
                        .prefix("/")
                        .bucket(config.getBucketName())
                        .build()
        );
        objects.contents().forEach(o -> {
                    System.out.println(o);
                    s3Client.deleteObject(DeleteObjectRequest.builder()
                            .bucket(config.getBucketName())
                            .key(o.key())
                            .build());
                }
        );

        s3Client.close();
    }

    public void deleteObject(String key) {
        S3Client s3Client = gimmeClient();
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(config.getBucketName())
                .key(key)
                .build());
        s3Client.close();
    }


    public void putObject() throws IOException {
        S3Client s3Client = gimmeClient();
        BucketService.class
                .getResourceAsStream("/myfile.txt");
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(config.getBucketName())
                        .key("/myfile.txt")
                        .build(),
                RequestBody.fromString("/myfile.txt")
        );
    }
}

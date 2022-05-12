package com.sergax.springboot_restapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * by Aksenchenko Serhii on 01.05.2022
 */

@ConfigurationProperties("aws")
@Data
@Configuration
public class S3ClientConfig {
    private String accessId;
    private String secretKey;
    private String bucketName;

    @Bean
    public S3Client gimmeClient() {
        Region region = Region.EU_CENTRAL_1;
        return S3Client.builder()
                .region(region)
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        getAccessId(),
                                        getSecretKey()
                                )
                        )
                )
                .build();
    }
}

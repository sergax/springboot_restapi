package com.sergax.springboot_restapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * by Aksenchenko Serhii on 22.04.2022
 */

@Configuration
public class AWSClientConfig {
    public AWSCredentials getCredentials() {
        return new BasicAWSCredentials(
                "<AWS accesskey>",
                "<AWS secretkey>"
        );
    }

    @Bean
    public AmazonS3 getS3client() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}

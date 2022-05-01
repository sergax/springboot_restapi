package com.sergax.springboot_restapi.config;

import com.sergax.springboot_restapi.properties.AWSS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * by Aksenchenko Serhii on 22.04.2022
 */

@Configuration
@RequiredArgsConstructor
public class AWSClientConfig {
    private final AWSS3Properties properties;
//
//    @Bean
//    public AmazonS3 amazonS3Client() {
//        AWSCredentials credentials = new BasicAWSCredentials(
//                properties.getAwsId(),
//                properties.getAwsKey()
//        );
//
//        return AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(properties.getRegion())
//                .build();
//    }
}

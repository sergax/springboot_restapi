package com.sergax.springboot_restapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}

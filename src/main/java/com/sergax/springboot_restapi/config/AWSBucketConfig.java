package com.sergax.springboot_restapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * by Aksenchenko Serhii on 22.04.2022
 */

@ConfigurationProperties("filebucket")
@Data
public class AWSBucketConfig {
    private String awsId;
    private String awsKey;
    private String bucketName;
}

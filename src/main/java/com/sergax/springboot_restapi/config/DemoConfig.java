package com.sergax.springboot_restapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;

/**
 * by Aksenchenko Serhii on 01.05.2022
 */

@ConfigurationProperties("aws")
@Data
@Configuration
public class DemoConfig {
    private String accessId = "AKIA5HK2KTGYXJYD2ENI";
    private String secretKey = "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";
    private String bucketName = "filebucketrestapi";
}

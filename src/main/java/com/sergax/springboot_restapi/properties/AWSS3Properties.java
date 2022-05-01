package com.sergax.springboot_restapi.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * by Aksenchenko Serhii on 01.05.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("filebucket")
@Configuration
public class AWSS3Properties {
    private String awsId;
    private String awsKey;
    private String region;
    private String bucketName;
}





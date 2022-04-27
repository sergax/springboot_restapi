package com.sergax.springboot_restapi.service;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.sergax.springboot_restapi.model.File;

import java.util.Optional;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface AWSService {

    S3Object upload(String bucketName, String path);

    ObjectListing fileList(String bucketName);

    S3Object getS3Object(String bucketName, String key);

}

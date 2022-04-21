package com.sergax.springboot_restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergax.springboot_restapi.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class FileDto {
    private Long id;
    private String fileName;
    private String location;

    public static FileDto fromFile(File file) {
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setFileName(file.getFileName());
        fileDto.setLocation(fileDto.getLocation());
        return fileDto;
    }
}

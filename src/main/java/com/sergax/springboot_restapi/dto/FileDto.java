package com.sergax.springboot_restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergax.springboot_restapi.model.Event;
import com.sergax.springboot_restapi.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class FileDto {
    private Long id;
    private String fileName;
    private String location;
    private String type;
    private LocalDateTime lastModified;
    private Float size;

    public static FileDto fromFile(File file) {
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setFileName(file.getFileName());
        fileDto.setLocation(file.getLocation());
        fileDto.setType(file.getType());
        fileDto.setLastModified(file.getLastModified());
        fileDto.setSize(file.getSize());

        return fileDto;
    }
}

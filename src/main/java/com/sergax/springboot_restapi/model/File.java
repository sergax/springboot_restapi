package com.sergax.springboot_restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @UpdateTimestamp
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "size")
    private Float size;
}

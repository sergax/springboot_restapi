package com.sergax.springboot_restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}

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
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User users;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private File file;
}

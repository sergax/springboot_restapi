package com.sergax.springboot_restapi.repository;

import com.sergax.springboot_restapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * by Aksenchenko Serhii on 17.04.2022
 */

public interface EventRepository extends JpaRepository<Event, Long> {
}

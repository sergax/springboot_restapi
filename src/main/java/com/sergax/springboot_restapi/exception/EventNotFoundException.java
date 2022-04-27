package com.sergax.springboot_restapi.exception;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Couldn't find Event by this id : " + id);
    }

    public EventNotFoundException(String message) {
        super(message);
    }
}

package com.sergax.springboot_restapi.dto;

import lombok.Data;

/**
 * by Aksenchenko Serhii on 19.04.2022
 */

@Data
public class AuthenticationRequestDto {
    private String login;
    private String password;
}

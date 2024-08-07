package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponse {
    private String message;
    private boolean isLoggedIn = false;
}
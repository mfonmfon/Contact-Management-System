package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserResponse {
    private boolean isLoggedIn = false;
    private String message;

}
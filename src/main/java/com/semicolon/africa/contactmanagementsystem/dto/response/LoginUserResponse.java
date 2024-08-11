package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserResponse {
    private boolean isLoggedIn;
    private String message;
    private String email;
    private String phoneNumber;

}
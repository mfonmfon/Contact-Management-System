package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponse {
    private String message;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

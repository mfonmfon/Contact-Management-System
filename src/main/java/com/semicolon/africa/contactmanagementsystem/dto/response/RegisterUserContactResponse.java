package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class RegisterUserContactResponse {
    private String message;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

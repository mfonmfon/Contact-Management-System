package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateContactResponse {
    private String message;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
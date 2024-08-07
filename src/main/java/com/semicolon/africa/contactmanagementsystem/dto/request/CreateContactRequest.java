package com.semicolon.africa.contactmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {
    private String message;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}

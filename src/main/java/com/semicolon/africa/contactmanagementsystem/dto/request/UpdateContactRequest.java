package com.semicolon.africa.contactmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
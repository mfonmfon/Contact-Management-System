package com.semicolon.africa.contactmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddContactResponse {
    private String contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private  String email;
    private String message;
}
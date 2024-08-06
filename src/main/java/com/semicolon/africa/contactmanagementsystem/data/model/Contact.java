package com.semicolon.africa.contactmanagementsystem.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Contact {
    private String id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
}

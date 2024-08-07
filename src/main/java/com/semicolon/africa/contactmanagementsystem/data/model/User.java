package com.semicolon.africa.contactmanagementsystem.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    private String id;
    private boolean isLoggedIn;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @DBRef
    List<Contact> contactList = new ArrayList<>();
}
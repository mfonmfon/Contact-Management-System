package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.dto.request.*;
import com.semicolon.africa.contactmanagementsystem.dto.response.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterUserResponse signUp(RegisterUserRequest register);

    LoginUserResponse login(LoginUserRequest request);

    LogoutResponse logout(LogoutRequest logoutRequest);

    AddContactResponse addContact(AddContactsRequest request);

    ShareContactResponse shareContact(ShareContactRequest request);

    DeleteContactResponse deleteContact(AddContactsRequest request);

    UpdateContactResponse editContact(AddContactsRequest request);

}

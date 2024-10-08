package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.User;
import com.semicolon.africa.contactmanagementsystem.dto.request.*;
import com.semicolon.africa.contactmanagementsystem.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    RegisterUserResponse signUp(RegisterUserRequest register);

    LoginUserResponse login(LoginUserRequest request);

    LogoutResponse logout(LogoutRequest logoutRequest);

    AddContactResponse addContact(AddContactsRequest request);

    ShareContactResponse shareContact(ShareContactRequest request);

    DeleteContactResponse deleteContact(String phoneNumber);

    UpdateContactResponse editContact(UpdateContactRequest request);

    List<User> getAllContacts(String userId);

}

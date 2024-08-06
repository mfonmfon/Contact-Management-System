package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterUserContactResponse signUp(RegisterUserContactRequest register);

    LoginUserResponse login(LoginUserRequest request);

}

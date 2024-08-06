package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.User;
import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserContactResponse signUp(RegisterUserContactRequest register) {
        User user = new User();
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setEmail(register.getEmail());
        userRepository.save(user);
        RegisterUserContactResponse response = new RegisterUserContactResponse();
        response.setUserId(user.getId());
        response.setMessage(" Successfully SignUp ");
        return response;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08147995494");
        request.setFirstName("Mfon");
        LoginUserResponse response = new LoginUserResponse();
        response.setMessage("Login Successfully");
        return response;
    }



}

package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.User;
import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LogoutRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.LogoutResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import com.semicolon.africa.contactmanagementsystem.exception.UserEmailException;
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
        User user = validateEmail(request.getEmail());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
        LoginUserResponse response = new LoginUserResponse();
        response.setLoggedIn(true);
        response.setMessage("Successfully LogIn");
        return response;
    }
    private User validateEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserEmailException("Email Not Found"));
    }
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        User user = validateEmail(request.getEmail());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        LogoutResponse logout = new LogoutResponse();
        logout.setLoggedIn(false);
        logout.setMessage("Successfully Logout");
        return logout;
    }

}

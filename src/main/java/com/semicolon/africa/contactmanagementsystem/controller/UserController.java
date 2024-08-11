package com.semicolon.africa.contactmanagementsystem.controller;

import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LogoutRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.*;
import com.semicolon.africa.contactmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request){
        try {
            RegisterUserResponse response = userService.signUp(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response), HttpStatus.CREATED);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ContactApiResponse(false, exception), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/log-in")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request){
        try {
            LoginUserResponse response = userService.login(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response), HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity<?> logout(LogoutRequest request){
        try {
            LogoutResponse response = userService.logout(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response),
                    HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add-contact")
    public ResponseEntity<?> addContact(@RequestBody AddContactsRequest request){
        try {
            AddContactResponse response = userService.addContact(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response),
                    HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),
                    HttpStatus.BAD_REQUEST);
        }
    }
}

package com.semicolon.africa.contactmanagementsystem.controller;

import com.semicolon.africa.contactmanagementsystem.dto.request.*;
import com.semicolon.africa.contactmanagementsystem.dto.response.*;
import com.semicolon.africa.contactmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping
    public ResponseEntity<?> editContact(@RequestBody UpdateContactRequest request){
        try {
            UpdateContactResponse response = userService.editContact(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response),
                    HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact( @RequestBody DeleteContactRequest request){
        try {
           DeleteContactResponse response = userService.deleteContact(request);
           return new ResponseEntity<>(new ContactApiResponse(true, response),
                   HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request){
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
}

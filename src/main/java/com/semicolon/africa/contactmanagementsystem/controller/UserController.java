package com.semicolon.africa.contactmanagementsystem.controller;

import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.ContactApiResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import com.semicolon.africa.contactmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sigUp")
@RequiredArgsConstructor
@RequestMapping
public class UserController {


    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserContactRequest request){
        try {
            RegisterUserContactResponse response = userService.signUp(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response), HttpStatus.CREATED);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ContactApiResponse(false, exception), HttpStatus.BAD_REQUEST);
        }
    }
}

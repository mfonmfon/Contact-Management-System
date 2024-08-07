package com.semicolon.africa.contactmanagementsystem.controller;

import com.semicolon.africa.contactmanagementsystem.dto.response.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.ContactApiResponse;
import com.semicolon.africa.contactmanagementsystem.services.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController("/api/vi/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactsService contactsService;

    @PostMapping("/create-contact")
    public ResponseEntity<?> addContact(@RequestBody AddContactsRequest request){
        try {
            AddContactResponse response = contactsService.createContact(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("edit-contact")
    public ResponseEntity<?> updateContact(@RequestBody UpdateContactRequest request){
        try {
            UpdateContactResponse response = contactsService.updateContact(request);
            return new ResponseEntity<>(new ContactApiResponse(true, response), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<?> deleteContact(@PathVariable String phoneNumber){
        try {
            DeleteContactResponse response = contactsService.deleteByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(new ContactApiResponse(true,response), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception), HttpStatus.BAD_REQUEST);
        }
    }
}

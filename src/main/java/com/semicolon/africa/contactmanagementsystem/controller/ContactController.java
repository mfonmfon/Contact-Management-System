package com.semicolon.africa.contactmanagementsystem.controller;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.data.repository.ContactRepository;
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

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@RestController("/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactsService contactsService;

    @PostMapping("/add-contact")
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

    @GetMapping("/getAllContacts")
    public ResponseEntity<?> allContacts(){
        try {
            List<Contact> allContacts = contactsService.findAll();
            return new ResponseEntity<>(new ContactApiResponse(true, allContacts),
                    HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ContactApiResponse(false, exception),
                    HttpStatus.BAD_REQUEST);
        }
    }
}

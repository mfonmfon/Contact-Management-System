package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.dto.response.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactsService {
    AddContactResponse createContact(AddContactsRequest request);

    UpdateContactResponse updateContact(UpdateContactRequest request);

    DeleteContactResponse deleteByPhoneNumber(String id);

    List<Contact> getAllContacts();

    Contact findById(String contactId);

}

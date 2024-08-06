package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.dto.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import org.springframework.stereotype.Service;

@Service
public interface ContactsService {
    AddContactResponse createContact(AddContactsRequest request);

    UpdateContactResponse updateContact(UpdateContactRequest request);

    DeleteContactResponse deleteByPhoneNumber(String id);

}

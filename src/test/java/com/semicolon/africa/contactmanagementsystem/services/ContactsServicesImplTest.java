package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.data.repository.ContactRepository;
import com.semicolon.africa.contactmanagementsystem.dto.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactsServicesImplTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactsService contactsService;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void testThatCanCreateContact(){
        AddContactsRequest request  = new AddContactsRequest();
        request.setFirstName("Mfon");
        request.setLastName("Mfon");
        request.setPhoneNumber("08147995494");
        request.setEmail("mfonm579@gmail.com");
        AddContactResponse response = contactsService.createContact(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("Contact created successful");
    }

    @Test
    public void testThatContactCanBeUpdated(){
        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("New Mfon");
        request.setLastName("New Mfon");
        request.setEmail("mfonm579@gmail.com");
        request.setPhoneNumber("08123115688");
        UpdateContactResponse response = contactsService.updateContact(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("Contact updated");
    }

    @Test
    public void testThatContactCanBeDeletedById(){
        AddContactsRequest request = new AddContactsRequest();
        request.setFirstName("Mfon");
        request.setLastName("Mfon");
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08123115688");
        String id = contactsService.createContact(request).getContactId();
        contactRepository.deleteById(id);
        DeleteContactResponse response1 = new DeleteContactResponse();
        assertThat(response1.getMessage()).contains("Deleted Successfully");
    }


}
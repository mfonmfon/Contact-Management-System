package com.semicolon.africa.contactmanagementsystem.data.repository;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp(){
        contactRepository.deleteAll();
    }

    @Test
    public void testThatRepositoryCanSavContacts(){
        AddContactsRequest request = new AddContactsRequest();
        request.setFirstName("Eniola");
        request.setLastName("Janet");
        request.setEmail("eniolajanet@gmail.com");
        request.setPhoneNumber("0813244532");
        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact = contactRepository.save(contact);
        assertThat(contact).isNotNull();
        assertThat(contact.getId());
        System.out.println(contact.getId());
        assertThat(contactRepository.count()).isEqualTo(1);
    }

}
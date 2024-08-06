package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.data.repository.ContactRepository;
import com.semicolon.africa.contactmanagementsystem.dto.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import com.semicolon.africa.contactmanagementsystem.exception.PhoneNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsServicesImpl implements ContactsService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public AddContactResponse createContact(AddContactsRequest request) {
        Contact contact = new Contact();
        validatePhoneNumber(request.getPhoneNumber());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contactRepository.save(contact);
        AddContactResponse response = new AddContactResponse();
        response.setContactId(contact.getId());
        response.setFirstName(contact.getFirstName());
        response.setEmail(contact.getEmail());
        response.setLastName(contact.getLastName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setMessage("Contact created successful");
        return response;
    }
    private void validatePhoneNumber(String phoneNumber){
        boolean isPhoneNumberExist = contactRepository.existsByPhoneNumber(phoneNumber);
        if (isPhoneNumberExist)throw new PhoneNumberException("Phone number already exists");
    }
    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest request) {
        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        UpdateContactResponse response = new UpdateContactResponse();
        response.setMessage("Contact updated");
        return response;
    }
    @Override
    public DeleteContactResponse deleteByPhoneNumber(String phoneNumber) {
        Contact contact = findContactByPhoneNumber(phoneNumber);
        contactRepository.delete(contact);
        DeleteContactResponse response = new DeleteContactResponse();
        response.setMessage("Contact deleted");
        return response;
    }

    private Contact findContactByPhoneNumber(String phoneNumber) {
        Optional<String> checkForP
        Contact contact = contactRepository.findByPhoneNumber(phoneNumber).orElseThrow()
    }

    private Contact findById(String id) {
        return contactRepository.findById(id).
                orElseThrow(()->new PhoneNumberException("Contact Not Found"));
    }
}

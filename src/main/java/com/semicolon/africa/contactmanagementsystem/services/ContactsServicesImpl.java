package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.data.repository.ContactRepository;
import com.semicolon.africa.contactmanagementsystem.dto.response.DeleteContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.UpdateContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.UpdateContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import com.semicolon.africa.contactmanagementsystem.exception.PhoneNumberException;
import com.semicolon.africa.contactmanagementsystem.exception.PhoneNumberExistException;
import com.semicolon.africa.contactmanagementsystem.exception.findingContactByIdException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactsServicesImpl implements ContactsService{
    private final ContactRepository contactRepository;

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
        response.setFirstName(contact.getFirstName());
        response.setEmail(contact.getEmail());
        response.setLastName(contact.getLastName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setMessage("Contact created successful");
        return response;
    }
    private void validatePhoneNumber(String phoneNumber){
        boolean isPhoneNumberExist = contactRepository.existsByPhoneNumber(phoneNumber);
        if (isPhoneNumberExist)throw new PhoneNumberExistException("Phone number already exists");
    }
    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest request) {
        Contact contact = findContactByPhoneNumber(request.getPhoneNumber());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contactRepository.save(contact);
        UpdateContactResponse response = new UpdateContactResponse();
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setEmail(contact.getEmail());
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
    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    private Contact findContactByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()->new PhoneNumberException("PhoneNumber Not Found"));
    }
    private Contact findById(String id) {
        return contactRepository.findById(id).
                orElseThrow(()->new findingContactByIdException("ID Not Found"));
    }
}

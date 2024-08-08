package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.controller.ContactController;
import com.semicolon.africa.contactmanagementsystem.data.model.Contact;
import com.semicolon.africa.contactmanagementsystem.data.model.User;
import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.*;
import com.semicolon.africa.contactmanagementsystem.dto.response.*;
import com.semicolon.africa.contactmanagementsystem.exception.PhoneNumberException;
import com.semicolon.africa.contactmanagementsystem.exception.UserEmailException;
import com.semicolon.africa.contactmanagementsystem.exception.findingContactByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ContactsService contactsService;

    @Override
    public RegisterUserResponse signUp(RegisterUserRequest register) {
        User user = new User();
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setEmail(register.getEmail());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setUserId(user.getId());
        response.setMessage(" Successfully SignUp ");
        return response;
    }
    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = findUserByEmail(request.getEmail());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
        LoginUserResponse response = new LoginUserResponse();
        response.setLoggedIn(true);
        response.setMessage("Successfully LogIn");
        return response;
    }
    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserEmailException("Invalid Details"));
    }
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        User user = findUserByEmail(request.getEmail());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        LogoutResponse logout = new LogoutResponse();
        logout.setLoggedIn(false);
        logout.setMessage("Successfully Logout");
        return logout;
    }

    @Override
    public AddContactResponse addContact(AddContactsRequest request) {
        AddContactResponse response = contactsService.createContact(request);
        Contact contact = contactsService.findById(response.getContactId());
        User user = findUserByEmail(response.getEmail());
        user.getContactList().add(contact);
        userRepository.save(user);
        return response;
    }
    @Override
    public ShareContactResponse shareContact(ShareContactRequest request) {
        Contact contact = new Contact();
        contact.setPhoneNumber(request.getContactId());
        User receiver = findById(request.getUserId());
        receiver.getContactList().add(contact);
        userRepository.save(receiver);
        ShareContactResponse response = new ShareContactResponse();
        response.setMessage("Contact Shared...");
        return response;
    }
    private User findById(String userId) {
        return userRepository.findById(userId).
                orElseThrow(()->new findingContactByIdException("ID NOT FOUND"));
    }
    @Override
    public DeleteContactResponse deleteContact(AddContactsRequest request) {
        DeleteContactResponse response = contactsService.deleteContactWith(request.getContactId());
//        Contact contact = contactsService.searchContactByUserPhoneNumber(response);
        return null;
    }
    private User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).
                orElseThrow(()-> new PhoneNumberException("Phone number not found"));
    }

    @Override
    public UpdateContactResponse editContact(UpdateContactRequest request) {
        AddContactsRequest contactsRequest = new AddContactsRequest();
        AddContactsRequest contactsResponse = contactsService.createContact(contactsRequest)
        UpdateContactResponse response = contactsService.updateContact(request);


        return null;
    }

}

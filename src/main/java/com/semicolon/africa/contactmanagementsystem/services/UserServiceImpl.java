package com.semicolon.africa.contactmanagementsystem.services;

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

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ContactsService contactsService;

    @Override
    public RegisterUserResponse signUp(RegisterUserRequest register) {
        User user = new User();
        validatePhoneNumber(user.getPhoneNumber());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setEmail(register.getEmail());
        user.setPhoneNumber(register.getPhoneNumber());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setMessage(" Successfully SignUp ");
        return response;
    }
    private void validatePhoneNumber(String phoneNumber) {
      boolean  isPhoneNumberExist = userRepository.existsByPhoneNumber(phoneNumber);
      if (isPhoneNumberExist){
          throw  new PhoneNumberException("Phone number already exist, Try again");
      }
    }
    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = findUserByEmail(request.getEmail());
//        validatePhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
        LoginUserResponse response = new LoginUserResponse();
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setLoggedIn(true);
        response.setMessage(user.getEmail() + "Successfully LogIn");
        return response;
    }
    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(()-> new UserEmailException("Email does not exist"));
    }
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        User user = findUserByEmail(request.getEmail());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        LogoutResponse logout = new LogoutResponse();
        logout.setMessage("Successfully Logout");
        return logout;
    }
    @Override
    public AddContactResponse addContact(AddContactsRequest request) {
        AddContactResponse response = contactsService.createContact(request);
        Contact contact = contactsService.findById(response.getContactId());
        User user = findUserByEmail(request.getEmail());
        List<Contact> contactList = user.getContactList();
        contactList.add(contact);
        user.setContactList(contactList);
        userRepository.save(user);
        return response;
    }
    @Override
    public ShareContactResponse shareContact(ShareContactRequest request) {
        Contact contact = new Contact();
        User receiver = findById(request.getReceiverId());
        receiver.setPhoneNumber(request.getReceiverId());
        receiver.getContactList().add(contact);
        userRepository.save(receiver);
        ShareContactResponse response = new ShareContactResponse();
        response.setMessage("Contact Shared to another user ......");
        return response;
    }
    private User findById(String userId) {
        return userRepository.findById(userId).
                orElseThrow(()->new findingContactByIdException("ID NOT FOUND"));
    }
    @Override
    public DeleteContactResponse deleteContact(String phoneNumber) {
        User user = findUserByPhoneNumber(phoneNumber);
        userRepository.delete(user);
        DeleteContactResponse response = new DeleteContactResponse();
        response.setMessage("Deleted Successfully");
        return response;
    }
    private User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).
                orElseThrow(()-> new PhoneNumberException("Phone number not found..."));
    }
    @Override
    public UpdateContactResponse editContact(UpdateContactRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        UpdateContactResponse response = new UpdateContactResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setMessage("Contact Updated Successfully");
        return response;
    }
    @Override
    public List<User> getAllContacts() {
        return userRepository.findAll();
    }
}
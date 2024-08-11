package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LogoutRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.AddContactResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.LogoutResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserCanRegister(){
        RegisterUserRequest register = new RegisterUserRequest();
        register.setFirstName("Paul");
        register.setLastName("Okon");
        register.setEmail("paulokon@gmail.com");
        register.setPhoneNumber("08123115788");
        RegisterUserResponse response = userService.signUp(register);
        assertThat(response.getMessage()).contains("Successfully SignUp");
    }

    @Test
    public void testThatUserCanLoginToAccessContact(){
        RegisterUserRequest register = registerUser();
        RegisterUserResponse response = userService.signUp(register);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains(" Successfully SignUp ");
        assertThat(userRepository.count()).isEqualTo(1L);
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08147995494");
        LoginUserResponse loginResponse = userService.login(request);
        assertTrue(loginResponse.isLoggedIn());
        assertThat(loginResponse.getMessage()).contains("Successfully LogIn");
    }

    private RegisterUserRequest registerUser() {
        RegisterUserRequest register = new RegisterUserRequest();
        register.setFirstName("Mfon");
        register.setLastName("Mfon");
        register.setEmail("mfon@gmail.com");
        register.setPhoneNumber("08147995494");
        return register;
    }
    @Test
    public void testThatUserCanLogin(){
        RegisterUserRequest register = registerUser();
        RegisterUserResponse response =userService.signUp(register);;
        assertThat(response).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1L);
        LoginUserRequest request = loginChecks();
        LoginUserResponse loginUserResponse = userService.login(request);
        assertTrue(loginUserResponse.isLoggedIn());
        assertThat(loginUserResponse.getMessage()).contains("Successfully LogIn");
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setEmail("mfon@gmail.com");
        LogoutResponse logoutResponse = userService.logout(logoutRequest);
        assertThat(logoutResponse.getMessage()).contains("Successfully Logout");

    }
    private LoginUserRequest loginChecks() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08135644321");
        return request;
    }
    @Test
    public void testThatUserCanAddPost(){
        AddContactsRequest request = new AddContactsRequest();
        request.setFirstName("Paul");
        request.setLastName("Uche");
        request.setEmail("pauluche@gmail.com");
        request.setPhoneNumber("09144477788");
        AddContactResponse response = userService.addContact(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("Contact Created Successfully");
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    public void testThatUserCanNotHaveTheSamePhoneNumber(){
        AddContactsRequest request = new AddContactsRequest();
        request.setFirstName("Mfon");
        request.setLastName("LastName");
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08123115688");
        AddContactResponse response = userService.addContact(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("Contact created successful");
        assertThat(userRepository.count()).isEqualTo(1);
        AddContactsRequest request1 = new AddContactsRequest();
        request1.setEmail("paul@gmail.com");
        request1.setFirstName("Paul");
        request1.setLastName("Blessing");
        request1.setPhoneNumber("08123115688");
        AddContactResponse response1 = userService.addContact(request1);
        assertThat(response1).isNotNull();
        assertThat(response1.getMessage()).contains("Contact created successful");


    }

    @Test
    public void testThatUserCanNotHaveTheSameEmail(){


    }

    @Test
    public void testThatUserCanAddAndEditPost(){


    }

    @Test
    public void testThatUserCanShareContactToAnotherUser(){

    }

    @Test
    public void testThatUserCanFindContactByPhoneNumber(){

    }
    @Test
    public void testThatUserCanAddAndDeleteContactByPhoneNumber(){

    }
}
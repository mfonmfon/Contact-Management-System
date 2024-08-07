package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.repository.ContactRepository;
import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.AddContactsRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.LogoutRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.LogoutResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        RegisterUserContactRequest register = new RegisterUserContactRequest();
        register.setFirstName("Paul");
        register.setLastName("Okon");
        register.setEmail("paulokon@gmail.com");
        register.setPhoneNumber("08123115788");
        RegisterUserContactResponse response = userService.signUp(register);
        response.setUserId(register.getId());
        assertThat(response.getMessage()).contains("Successfully SignUp");
    }

    @Test
    public void testThatUserCanLoginToAccessContact(){
        RegisterUserContactRequest register = registerUser();
        RegisterUserContactResponse response = userService.signUp(register);
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

    private RegisterUserContactRequest registerUser() {
        RegisterUserContactRequest register = new  RegisterUserContactRequest();
        register.setFirstName("Mfon");
        register.setLastName("Mfon");
        register.setEmail("mfon@gmail.com");
        register.setPhoneNumber("08147995494");
        return register;
    }

    @Test
    public void testThatUserCanLogin(){
        RegisterUserContactRequest register = registerUser();
        RegisterUserContactResponse response =userService.signUp(register);;
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
}
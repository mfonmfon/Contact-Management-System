package com.semicolon.africa.contactmanagementsystem.services;

import com.semicolon.africa.contactmanagementsystem.data.repository.UserRepository;
import com.semicolon.africa.contactmanagementsystem.dto.request.LoginUserRequest;
import com.semicolon.africa.contactmanagementsystem.dto.request.RegisterUserContactRequest;
import com.semicolon.africa.contactmanagementsystem.dto.response.LoginUserResponse;
import com.semicolon.africa.contactmanagementsystem.dto.response.RegisterUserContactResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("mfon@gmail.com");
        request.setPhoneNumber("08147995494");
        request.setFirstName("Mfon");
        LoginUserResponse response = userService.login(request);
        assertThat(response.getMessage()).contains("Login Successfully");
    }
}
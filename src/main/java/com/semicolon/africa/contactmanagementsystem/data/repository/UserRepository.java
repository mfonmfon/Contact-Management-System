package com.semicolon.africa.contactmanagementsystem.data.repository;

import com.semicolon.africa.contactmanagementsystem.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends MongoRepository<User, String> {
}

package com.semicolon.africa.contactmanagementsystem.data.repository;

import com.semicolon.africa.contactmanagementsystem.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByEmail(String email);

  Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}

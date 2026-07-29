package com.management.bloodbank.service;

import com.management.bloodbank.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
    boolean emailExists(String email);
}
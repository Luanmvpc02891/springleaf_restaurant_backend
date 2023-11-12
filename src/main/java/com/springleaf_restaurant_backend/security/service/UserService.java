package com.springleaf_restaurant_backend.security.service;

import java.util.List;
import java.util.Optional;

import com.springleaf_restaurant_backend.security.entities.User;

public interface UserService {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}
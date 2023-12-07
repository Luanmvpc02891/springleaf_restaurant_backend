package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@RestController
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @PostMapping("/manager/users")
    public ResponseEntity<List<User>> getUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/manager/update/users")
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    // @DeleteMapping("/manager/delete/user/{id}")
    // public ResponseEntity<User> deleteUser(
    // @RequestBody User user,
    // @RequestHeader("Authorization") String token
    // ) {
    // return ResponseEntity.ok(userRepository.save(user));
    // }

}

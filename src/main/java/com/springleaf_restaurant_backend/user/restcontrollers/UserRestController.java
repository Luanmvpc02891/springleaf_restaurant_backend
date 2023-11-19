package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@RestController
public class UserRestController {
    @Autowired
    private UserRepository userRepository;
    
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    
}

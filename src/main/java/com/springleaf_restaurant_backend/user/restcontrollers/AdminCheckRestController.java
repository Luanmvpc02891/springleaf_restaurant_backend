package com.springleaf_restaurant_backend.user.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@RestController
public class AdminCheckRestController {

    @Autowired
    UserRepository userRepository;

    
}

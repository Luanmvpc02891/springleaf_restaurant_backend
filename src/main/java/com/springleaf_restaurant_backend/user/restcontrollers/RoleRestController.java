package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.entities.Role;
import com.springleaf_restaurant_backend.security.repositories.RoleRepository;

@RestController
@RequestMapping("/public")
public class RoleRestController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}

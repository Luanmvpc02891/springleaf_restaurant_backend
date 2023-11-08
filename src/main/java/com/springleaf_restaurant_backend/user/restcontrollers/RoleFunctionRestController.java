package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.repositories.RoleFunctionRepository;
import com.springleaf_restaurant_backend.user.entities.RoleFunction;

@RestController
public class RoleFunctionRestController {
    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    @GetMapping("/public/roleFunctions")
    public List<RoleFunction> getRoleFuns() {
        return roleFunctionRepository.findAll();
    }
}

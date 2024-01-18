package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.entities.UserRole;
import com.springleaf_restaurant_backend.security.service.UserRoleService;

@RestController
public class UserRoleRestController {
    
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/public/userRoles")
    public List<UserRole> getRoles() {
        return userRoleService.getAllUserRoles();
    }

    @GetMapping("/public/userRole/{userRoleId}")
    public Optional<UserRole> getTableById(@PathVariable("userRoleId") Long userRoleId) {
        return userRoleService.getUserRoleById(userRoleId);
    }

    @PostMapping("/public/create/userRole")
    public UserRole createRestaurantTable(@RequestBody UserRole restaurantTable) {
        return userRoleService.createUserRole(restaurantTable);
    }

    @PutMapping("/public/update/userRole")
    public UserRole updateTable(@RequestBody UserRole updatedTable) {
        return userRoleService.createUserRole(updatedTable);
    }

    @DeleteMapping("/public/delete/userRole/{userRoleId}")
    public void deleteInventory(@PathVariable("userRoleId") Long userRoleId) {
        userRoleService.deleteUserRole(userRoleId);
    }
}

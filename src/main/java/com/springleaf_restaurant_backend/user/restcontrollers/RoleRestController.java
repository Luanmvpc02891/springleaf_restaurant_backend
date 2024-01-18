package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.entities.Role;
import com.springleaf_restaurant_backend.security.service.RoleService;

@RestController
public class RoleRestController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/public/roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/public/role/{tableId}")
    public Optional<Role> getTableById(@PathVariable("roleId") Integer tableId) {
        return roleService.getRoleById(tableId);
    }

    @PostMapping("/public/create/role")
    public Role createRestaurantTable(@RequestBody Role restaurantTable) {
        return roleService.createRole(restaurantTable);
    }

    @PutMapping("/public/update/role")
    public Role updateTable(@RequestBody Role updatedTable) {
        return roleService.createRole(updatedTable);
    }

    @DeleteMapping("/public/delete/role/{roleId}")
    public void deleteInventory(@PathVariable("tableId") Integer tableId) {
        roleService.deleteRole(tableId);
    }

}

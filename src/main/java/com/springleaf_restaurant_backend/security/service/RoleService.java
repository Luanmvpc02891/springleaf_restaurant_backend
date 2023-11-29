package com.springleaf_restaurant_backend.security.service;

import java.util.List;
import java.util.Optional;

import com.springleaf_restaurant_backend.security.entities.Role;
 
public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role findByRoleName(String roleName);
    Optional<Role> getRoleById(Integer id);
    Role updateRole(Integer id, Role role);
    void deleteRole(Integer id);
}
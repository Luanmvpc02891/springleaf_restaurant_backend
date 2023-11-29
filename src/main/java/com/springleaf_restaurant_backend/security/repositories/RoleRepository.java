package com.springleaf_restaurant_backend.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.springleaf_restaurant_backend.security.entities.Role;
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}

package com.springleaf_restaurant_backend.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.security.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    List<String> findRoleNamesByUserId(Long userId);
}

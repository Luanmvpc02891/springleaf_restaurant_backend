package com.springleaf_restaurant_backend.security.service;

import java.util.List;
import java.util.Optional;

import com.springleaf_restaurant_backend.security.entities.UserRole;

public interface UserRoleService {
    UserRole createUserRole(UserRole userRole);
    List<UserRole> getAllUserRoles();
    Optional<UserRole> getUserRoleById(Long userRoleId);
    UserRole updateUserRole(Long userRoleId, UserRole userRole);
    void deleteUserRole(Long userRoleId);
}
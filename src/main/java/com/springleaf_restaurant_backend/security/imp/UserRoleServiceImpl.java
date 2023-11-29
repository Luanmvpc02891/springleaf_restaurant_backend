package com.springleaf_restaurant_backend.security.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.security.entities.UserRole;
import com.springleaf_restaurant_backend.security.repositories.UserRoleRepository;
import com.springleaf_restaurant_backend.security.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository; 

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole createUserRole(UserRole UserRole) {
        return userRoleRepository.save(UserRole);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> getUserRoleById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public UserRole updateUserRole(Long id, UserRole updatedUserRole) {
        if (userRoleRepository.existsById(id)) {
            return userRoleRepository.save(updatedUserRole);
        }
        return null; 
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

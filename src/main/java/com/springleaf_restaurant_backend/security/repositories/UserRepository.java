package com.springleaf_restaurant_backend.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springleaf_restaurant_backend.security.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); 

    User findByEmail(String email);
    @Query("SELECT r.roleName FROM User u " +
           "INNER JOIN UserRole ur ON ur.userId = u.userId " +
           "INNER JOIN Role r ON ur.roleId = r.roleId " +
           "WHERE u.userId = :userId")
    List<String> findRoleNamesByUserId(@Param("userId") Long userId);


}

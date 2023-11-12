package com.springleaf_restaurant_backend.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
   
    // @Query("SELECT r.roleSa FROM Role r WHERE r.roleId = :roleId")
    // String findRoleSaByRoleId(@Param("roleId") Integer roleId);

    @Query("SELECT r.roleName FROM Role r ")
    List<String> findRoleName();
}

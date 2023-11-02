package com.springleaf_restaurant_backend.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.RoleFunction;

public interface RoleFunctionRepository extends JpaRepository<RoleFunction, Integer> {

}

package com.springleaf_restaurant_backend.user.restcontrollers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.Major;

public interface MajorRepository extends JpaRepository<Major, Integer>{
    
}

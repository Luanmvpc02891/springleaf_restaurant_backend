package com.springleaf_restaurant_backend.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springleaf_restaurant_backend.user.entities.RestaurantTable;

import jakarta.transaction.Transactional;

public interface RestauranTableRepository extends JpaRepository<RestaurantTable, Long> {

}

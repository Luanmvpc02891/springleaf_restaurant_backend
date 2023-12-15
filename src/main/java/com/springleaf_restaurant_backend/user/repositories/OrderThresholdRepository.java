package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.OrderThreshold;

public interface OrderThresholdRepository extends JpaRepository<OrderThreshold, Long> {
  List<OrderThreshold> findByInventoryId(Long inventoryId);
  
}

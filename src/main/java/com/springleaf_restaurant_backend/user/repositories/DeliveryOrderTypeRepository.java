package com.springleaf_restaurant_backend.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderType;

public interface DeliveryOrderTypeRepository extends JpaRepository<DeliveryOrderType, Integer> {
    Optional<DeliveryOrderType> findByDeliveryOrderTypeName(String deliveryOrderTypeName);
}

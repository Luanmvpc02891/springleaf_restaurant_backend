package com.springleaf_restaurant_backend.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    DeliveryOrder findByCustomerId(Long userId);
    //Optional<DeliveryOrder> findByDeliveryOrderStatusId(Integer deliveryOrderStatusId);
}

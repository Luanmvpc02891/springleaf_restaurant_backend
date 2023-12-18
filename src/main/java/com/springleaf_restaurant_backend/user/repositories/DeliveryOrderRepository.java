package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    DeliveryOrder findByCustomerId(Long userId);
    DeliveryOrder findByCustomerIdAndDeliveryRestaurantId(Long userId, Long restaurantId);
    //Optional<DeliveryOrder> findByDeliveryOrderStatusId(Integer deliveryOrderStatusId);
    List<DeliveryOrder> findByCustomerIdAndDeliveryOrderTypeIdAndActive(Long customerId, Integer deliveryOrderTypeId, boolean active);
    Optional<DeliveryOrder> findByCustomerIdAndDeliveryOrderTypeIdAndActiveAndDeliveryRestaurantId(
            Long customerId, Integer deliveryOrderTypeId, boolean active, Long restaurantId);
}

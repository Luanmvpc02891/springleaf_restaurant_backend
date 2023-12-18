package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderService {
    DeliveryOrder getDeliveryOrderById(Long id);

    List<DeliveryOrder> getAllDeliveryOrders();

    DeliveryOrder saveDeliveryOrder(DeliveryOrder deliveryOrder);

    void deleteDeliveryOrder(Long id);

    DeliveryOrder findByCustomerId(Long userId);
    DeliveryOrder findByCustomerIdAndDeliveryRestaurantId(Long userId, Long restaurantId);

    List<DeliveryOrder> getDeliveryOrdersByUserIdAndTypeAndActive(Long userId, Integer deliveryOrderTypeId, boolean active);
    Optional<DeliveryOrder> getDeliveryOrdersByUserIdAndTypeAndActiveAndDeliveryRestaurantId(Long userId, Integer deliveryOrderTypeId, boolean active, Long restaurantId);
    // DeliveryOrder findByDeliveryOrderStatusId(Integer deliveryOrderStatusId);
}

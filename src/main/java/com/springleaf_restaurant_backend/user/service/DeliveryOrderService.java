package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrder getDeliveryOrderById(Long id);

    List<DeliveryOrder> getAllDeliveryOrders();

    DeliveryOrder saveDeliveryOrder(DeliveryOrder deliveryOrder);

    void deleteDeliveryOrder(Long id);

    void findByCustomerId(Long userId);
    // DeliveryOrder findByDeliveryOrderStatusId(Integer deliveryOrderStatusId);
}

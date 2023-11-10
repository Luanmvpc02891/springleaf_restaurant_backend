package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.User;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrder getDeliveryOrderById(Long id);
    List<DeliveryOrder> getAllDeliveryOrders();
    DeliveryOrder saveDeliveryOrder(DeliveryOrder deliveryOrder);
    DeliveryOrder updateDeliveryOrder(DeliveryOrder deliveryOrder);
    void deleteDeliveryOrder(Long id);
    void findByUser(User user);
}

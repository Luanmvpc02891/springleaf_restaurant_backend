package com.springleaf_restaurant_backend.user.service;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderService {
    DeliveryOrder getDeliveryOrderById(Long id);
    List<DeliveryOrder> getAllDeliveryOrders();
    DeliveryOrder saveDeliveryOrder(DeliveryOrder deliveryOrder);
    DeliveryOrder updateDeliveryOrder(DeliveryOrder deliveryOrder);
    void deleteDeliveryOrder(Long id);
    void findByCustomerId(Long userId);
    //DeliveryOrder findByDeliveryOrderStatusId(Integer deliveryOrderStatusId);
}

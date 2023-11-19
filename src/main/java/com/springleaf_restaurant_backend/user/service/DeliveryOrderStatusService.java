package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;

import java.util.List;

public interface DeliveryOrderStatusService {
    DeliveryOrderStatus getDeliveryOrderStatusById(Long id);

    // Optional<DeliveryOrderStatus> findByDeliveryOrderStatusName(String
    // deliveryOrderStatusName);
    List<DeliveryOrderStatus> getAllDeliveryOrderStatuses();

    DeliveryOrderStatus saveDeliveryOrderStatus(DeliveryOrderStatus deliveryOrderStatus);

    void deleteDeliveryOrderStatus(Long id);
}

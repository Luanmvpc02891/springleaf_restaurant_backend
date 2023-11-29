package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderStatusService {
    List<DeliveryOrderStatus> getAllDeliveryOrderStatuses();

    DeliveryOrderStatus getDeliveryOrderStatusById(Long id);

    DeliveryOrderStatus getDeliveryOrderStatusByName(String deliveryOrderStatusName);

    DeliveryOrderStatus saveDeliveryOrderStatus(DeliveryOrderStatus deliveryOrderStatus);

    void deleteDeliveryOrderStatus(Long id);
}

package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderType;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderTypeService {
    List<DeliveryOrderType> getAllDeliveryOrderTypes();
    
    Optional<DeliveryOrderType> getDeliveryOrderTypeById(Integer id);

    Optional<DeliveryOrderType> getDeliveryOrderTypeByName(String name);

    DeliveryOrderType createDeliveryOrderType(DeliveryOrderType deliveryOrderType);

    DeliveryOrderType updateDeliveryOrderType(Integer id, DeliveryOrderType updatedDeliveryOrderType);

    void deleteDeliveryOrderType(Integer id);
}

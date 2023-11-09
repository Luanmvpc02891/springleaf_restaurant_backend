package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderDetail;

import java.util.List;

public interface DeliveryOrderDetailService {
    DeliveryOrderDetail getDeliveryOrderDetailById(Long id);
    List<DeliveryOrderDetail> getAllDeliveryOrderDetails();
    DeliveryOrderDetail saveDeliveryOrderDetail(DeliveryOrderDetail deliveryOrderDetail);
    void updateDeliveryOrderDetail(DeliveryOrderDetail deliveryOrderDetail);
    void deleteDeliveryOrderDetail(Long id);
}

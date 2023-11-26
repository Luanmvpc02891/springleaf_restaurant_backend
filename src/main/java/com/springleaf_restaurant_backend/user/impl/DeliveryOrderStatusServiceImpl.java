package com.springleaf_restaurant_backend.user.impl;

import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderStatusRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderStatusService;

import java.util.List;

@Service
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {
    private final DeliveryOrderStatusRepository deliveryOrderStatusRepository;

    public DeliveryOrderStatusServiceImpl(DeliveryOrderStatusRepository deliveryOrderStatusRepository) {
        this.deliveryOrderStatusRepository = deliveryOrderStatusRepository;
    }

    @Override
    public DeliveryOrderStatus getDeliveryOrderStatusById(Long id) {
        return deliveryOrderStatusRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryOrderStatus> getAllDeliveryOrderStatuses() {
        return deliveryOrderStatusRepository.findAll();
    }

    @Override
    public DeliveryOrderStatus saveDeliveryOrderStatus(DeliveryOrderStatus deliveryOrderStatus) {
        return deliveryOrderStatusRepository.save(deliveryOrderStatus);
    }

    @Override
    public void deleteDeliveryOrderStatus(Long id) {
        deliveryOrderStatusRepository.deleteById(id);
    }

    @Override
    public DeliveryOrderStatus getDeliveryOrderStatusByName(String deliveryOrderStatusName) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getDeliveryOrderStatusByName'");
    }

}

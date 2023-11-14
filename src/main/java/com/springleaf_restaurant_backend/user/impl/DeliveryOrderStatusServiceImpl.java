package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderStatusRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderStatusService;

import java.util.List;

@Service
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {
    private final DeliveryOrderStatusRepository deliveryOrderStatusRepository;

    @Autowired
    public DeliveryOrderStatusServiceImpl(DeliveryOrderStatusRepository deliveryOrderStatusRepository) {
        this.deliveryOrderStatusRepository = deliveryOrderStatusRepository;
    }

    @Override
    public DeliveryOrderStatus getDeliveryOrderStatusById(Long id) {
        return deliveryOrderStatusRepository.findById(id).orElse(null);
    }

    @Override
    public DeliveryOrderStatus getDeliveryOrderStatusByName(String name) {
        return deliveryOrderStatusRepository.findByName(name).orElse(null);
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
    public DeliveryOrderStatus updateDeliveryOrderStatus(DeliveryOrderStatus deliveryOrderStatus) {
        return deliveryOrderStatusRepository.save(deliveryOrderStatus);
    }

    @Override
    public void deleteDeliveryOrderStatus(Long id) {
        deliveryOrderStatusRepository.deleteById(id);
    }
}

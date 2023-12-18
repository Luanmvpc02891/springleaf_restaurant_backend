package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderRepository;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryOrderServiceImpl(DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public DeliveryOrder getDeliveryOrderById(Long id) {
        return deliveryOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    @Override
    public DeliveryOrder saveDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Override
    public void deleteDeliveryOrder(Long id) {
        deliveryOrderRepository.deleteById(id);
    }

    @Override
    public DeliveryOrder findByCustomerId(Long id) {
        return deliveryOrderRepository.findByCustomerId(id);
    } 

    @Override
    public List<DeliveryOrder> getDeliveryOrdersByUserIdAndTypeAndActive(Long userId, Integer deliveryOrderTypeId, boolean active) {
        return deliveryOrderRepository.findByCustomerIdAndDeliveryOrderTypeIdAndActive(userId, deliveryOrderTypeId , active);
    }

    @Override
    public Optional<DeliveryOrder> getDeliveryOrdersByUserIdAndTypeAndActiveAndDeliveryRestaurantId(Long userId,
            Integer deliveryOrderTypeId, boolean active, Long restaurantId) {
        return deliveryOrderRepository.findByCustomerIdAndDeliveryOrderTypeIdAndActiveAndDeliveryRestaurantId(userId, deliveryOrderTypeId, active, restaurantId);
    }
    
}

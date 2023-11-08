package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.service.OrderThresholdService;

import java.util.List;

@Service
public class OrderThresholdServiceImpl implements OrderThresholdService {
    private final OrderThresholdRepository orderThresholdRepository;

    @Autowired
    public OrderThresholdServiceImpl(OrderThresholdRepository orderThresholdRepository) {
        this.orderThresholdRepository = orderThresholdRepository;
    }

    @Override
    public List<OrderThreshold> getAllOrderThresholds() {
        return orderThresholdRepository.findAll();
    }

    @Override
    public OrderThreshold getOrderThresholdById(Long id) {
        return orderThresholdRepository.findById(id).orElse(null);
    }

    @Override
    public OrderThreshold saveOrderThreshold(OrderThreshold orderThreshold) {
        return orderThresholdRepository.save(orderThreshold);
    }

    @Override
    public OrderThreshold updateOrderThreshold(OrderThreshold orderThreshold) {
        return orderThresholdRepository.save(orderThreshold);
    }

    @Override
    public void deleteOrderThreshold(Long id) {
        orderThresholdRepository.deleteById(id);
    }
}

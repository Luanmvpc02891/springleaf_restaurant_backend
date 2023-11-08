package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.OrderType;
import com.springleaf_restaurant_backend.user.repositories.OrderTypeRepository;
import com.springleaf_restaurant_backend.user.service.OrderTypeService;

import java.util.List;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
    private final OrderTypeRepository orderTypeRepository;

    @Autowired
    public OrderTypeServiceImpl(OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    @Override
    public List<OrderType> getAllOrderTypes() {
        return orderTypeRepository.findAll();
    }

    @Override
    public OrderType getOrderTypeById(Long id) {
        return orderTypeRepository.findById(id).orElse(null);
    }

    @Override
    public OrderType saveOrderType(OrderType orderType) {
        return orderTypeRepository.save(orderType);
    }

    @Override
    public OrderType updateOrderType(OrderType orderType) {
        return orderTypeRepository.save(orderType);
    }

    @Override
    public void deleteOrderType(Long id) {
        orderTypeRepository.deleteById(id);
    }
}

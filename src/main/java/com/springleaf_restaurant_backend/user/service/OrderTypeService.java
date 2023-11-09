package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.OrderType;

import java.util.List;

public interface OrderTypeService {
    List<OrderType> getAllOrderTypes();
    OrderType getOrderTypeById(Long id);
    OrderType saveOrderType(OrderType orderType);
    OrderType updateOrderType(OrderType orderType);
    void deleteOrderType(Long id);
}

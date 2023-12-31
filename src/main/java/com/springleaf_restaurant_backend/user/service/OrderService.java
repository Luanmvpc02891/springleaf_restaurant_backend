package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order saveOrder(Order order);

    void deleteOrder(Long id);

    Optional<Order> getOrdersByDeliveryOrderId(Long deliveryOrderId);

    Optional<Order> getOrdersByReservationId(Long reservationId);
}

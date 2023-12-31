package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();

    OrderDetail getOrderDetailById(Long id);

    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    void deleteOrderDetail(Long id);

    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);

    List<Object[]> getTop5MostOrderedItems();
}

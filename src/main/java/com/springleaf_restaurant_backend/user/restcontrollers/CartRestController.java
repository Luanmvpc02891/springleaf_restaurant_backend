package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.service.OrderService;

@RestController
public class CartRestController {
    @Autowired
    OrderService orderService;

    @GetMapping("/api/carts")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }
}

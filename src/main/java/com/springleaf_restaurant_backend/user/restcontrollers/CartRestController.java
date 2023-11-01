package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.repositories.OrderRepository;

@RestController
@RequestMapping("/api")
public class CartRestController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/carts")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}

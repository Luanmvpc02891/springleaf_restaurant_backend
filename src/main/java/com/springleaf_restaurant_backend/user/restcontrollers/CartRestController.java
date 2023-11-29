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

    @GetMapping("/public/carts")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/public/cart/{cartId}")
    public Order getOneOrder(@PathVariable("cartId") Long cartId) {
        return orderService.getOrderById(cartId);
    }

    @PostMapping("/public/create/cart")
    public Order createOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @PutMapping("/public/update/cart")
    public Order updateOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/public/delete/cart/{cartId}")
    public void deleteOrder(@PathVariable("cartId") Long cartId) {
        orderService.deleteOrder(cartId);
    }
}

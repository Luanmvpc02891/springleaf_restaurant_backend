package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.service.OrderThresholdService;

@RestController
public class OrderThresholdRestController {
    @Autowired
    OrderThresholdService orderThresholdService;

    @GetMapping("/public/orderThresholds")
    public List<OrderThreshold> getOrderThresholds() {
        return orderThresholdService.getAllOrderThresholds();
    }

    @GetMapping("/public/orderThreshold/{orderThresholdId}")
    public OrderThreshold getOneOrderThreshold(@PathVariable("orderThresholdId") Long orderThresholdId) {
        return orderThresholdService.getOrderThresholdById(orderThresholdId);
    }

    @PostMapping("/public/create/orderThreshold")
    public OrderThreshold createOrderThreshold(@RequestBody OrderThreshold orderThreshold) {
        return orderThresholdService.saveOrderThreshold(orderThreshold);
    }

    @PutMapping("/public/update/orderThreshold")
    public OrderThreshold updateOrderThreshold(@RequestBody OrderThreshold orderThreshold) {
        return orderThresholdService.saveOrderThreshold(orderThreshold);
    }

    @DeleteMapping("/public/delete/orderThreshold/{orderThresholdId}")
    public void deleteOrderThreshold(@PathVariable("orderThresholdId") Long orderThresholdId) {
        orderThresholdService.deleteOrderThreshold(orderThresholdId);
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderRepository;

@RestController
@RequestMapping("/api")
public class DeliveryOrderRestController {
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @GetMapping("/deliveryOrders")
    public List<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderStatus;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderStatusRepository;

@RestController
@RequestMapping("/api")
public class DeliveryOrderStatusRestController {
    @Autowired
    DeliveryOrderStatusRepository deliveryOrderStatusRepository;

    @GetMapping("/deliveryOrderStatuses")
    public List<DeliveryOrderStatus> getdeliveryOrderStatuses() {
        return deliveryOrderStatusRepository.findAll();
    }
}

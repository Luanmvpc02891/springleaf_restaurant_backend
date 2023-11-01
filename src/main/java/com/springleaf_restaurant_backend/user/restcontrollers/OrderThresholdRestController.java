package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.entities.OrderType;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderTypeRepository;

@RestController
@RequestMapping("/api")
public class OrderThresholdRestController {
    @Autowired
    OrderThresholdRepository orderThresholdRepository;

    @GetMapping("/orderThresholds")
    public List<OrderThreshold> getOrderThresholds() {
        return orderThresholdRepository.findAll();
    }
}

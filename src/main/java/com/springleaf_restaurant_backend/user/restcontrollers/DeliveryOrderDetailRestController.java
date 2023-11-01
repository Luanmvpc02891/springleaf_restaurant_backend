package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.DeliveryOrderDetail;
import com.springleaf_restaurant_backend.user.repositories.DeliveryOrderDetailRepository;

@RestController
@RequestMapping("/api")
public class DeliveryOrderDetailRestController {
    @Autowired
    DeliveryOrderDetailRepository deliveryOrderDetailRepository;

    @GetMapping("/deliveryOrderDetails")
    public List<DeliveryOrderDetail> getDeliveryOrderDetails() {
        return deliveryOrderDetailRepository.findAll();
    }
}

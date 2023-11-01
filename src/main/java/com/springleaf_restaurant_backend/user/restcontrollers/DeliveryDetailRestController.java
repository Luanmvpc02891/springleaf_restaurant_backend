package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.DeliveryDetail;
import com.springleaf_restaurant_backend.user.repositories.DeliveryDetailRepository;

@RestController
@RequestMapping("/api")
public class DeliveryDetailRestController {
    @Autowired
    DeliveryDetailRepository deliveryDetailRepository;

    @GetMapping("/deliveryDetails")
    public List<DeliveryDetail> getdeliveryDetails() {
        return deliveryDetailRepository.findAll();
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.OrderType;
import com.springleaf_restaurant_backend.user.repositories.OrderTypeRepository;

@RestController
@RequestMapping("/api")
public class OrderTypeRestController {
    @Autowired
    OrderTypeRepository orderTypeRepository;

    @GetMapping("/orderTypes")
    public List<OrderType> getOrderTypes() {
        return orderTypeRepository.findAll();
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.repositories.OrderDetailRepository;

@RestController
@RequestMapping("/api")
public class CartDetailRestController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/cartDetails")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }
}

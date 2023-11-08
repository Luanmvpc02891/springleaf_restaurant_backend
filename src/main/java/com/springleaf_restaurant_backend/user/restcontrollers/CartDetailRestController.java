package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;

@RestController
public class CartDetailRestController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/api/cartDetails")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
}

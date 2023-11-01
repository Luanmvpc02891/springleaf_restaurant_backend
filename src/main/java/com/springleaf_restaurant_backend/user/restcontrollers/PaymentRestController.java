package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Payment;
import com.springleaf_restaurant_backend.user.repositories.PaymentRepository;

@RestController
@RequestMapping("/api")
public class PaymentRestController {
    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public List<Payment> gPayments() {
        return paymentRepository.findAll();
    }
}

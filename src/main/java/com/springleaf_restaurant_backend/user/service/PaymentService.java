package com.springleaf_restaurant_backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.repositories.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
}

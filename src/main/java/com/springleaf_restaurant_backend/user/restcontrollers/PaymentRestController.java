package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Payment;
import com.springleaf_restaurant_backend.user.service.PaymentService;

@RestController
public class PaymentRestController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/public/payments")
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/public/payment/{paymentId}")
    public Payment getOnePayment(@PathVariable("paymentId") Long paymentId){
        return paymentService.getPaymentById(paymentId);
    }

    @PostMapping("/public/create/payment")
    public Payment createPayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }

    @PutMapping("/public/update/payment")
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }

    @DeleteMapping("/public/delete/payment/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") Long paymentId){
        paymentService.deletePayment(paymentId);
    }
}

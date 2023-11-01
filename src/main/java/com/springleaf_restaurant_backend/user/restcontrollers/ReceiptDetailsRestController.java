package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.ReceiptDetails;
import com.springleaf_restaurant_backend.user.repositories.ReceiptDetailsRepository;

@RestController
@RequestMapping("/api")
public class ReceiptDetailsRestController {
    @Autowired
    private ReceiptDetailsRepository receiptDetailsRepository;

    @GetMapping("/receiptDetails")
    public List<ReceiptDetails> getReceiptDetails() {
        return receiptDetailsRepository.findAll();
    }
}

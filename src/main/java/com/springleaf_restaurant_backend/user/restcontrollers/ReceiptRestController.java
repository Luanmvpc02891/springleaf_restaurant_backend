package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Receipt;
import com.springleaf_restaurant_backend.user.repositories.ReceiptRepository;

@RestController
@RequestMapping("/api")
public class ReceiptRestController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @GetMapping("/receipts")
    public List<Receipt> receipts() {
        return receiptRepository.findAll();
    }
}

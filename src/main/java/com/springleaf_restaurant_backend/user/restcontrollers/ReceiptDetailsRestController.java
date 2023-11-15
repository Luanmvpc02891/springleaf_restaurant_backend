package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.ReceiptDetails;
import com.springleaf_restaurant_backend.user.service.ReceiptDetailsService;

@RestController
public class ReceiptDetailsRestController {
    @Autowired
    private ReceiptDetailsService receiptDetailsService;

    @GetMapping("/public/receiptDetails")
    public List<ReceiptDetails> getReceiptDetails() {
        return receiptDetailsService.getAllReceiptDetails();
    }

    @GetMapping("/public/receiptDetail/{receiptDetailId}")
    public ReceiptDetails getReceipDetail(@PathVariable("receiptDetailId") Long receiptDetailId){
        return receiptDetailsService.getReceiptDetailsById(receiptDetailId);
    }

    @PostMapping("/public/create/receiptDetail")
    public ReceiptDetails createReceiptDetails(@RequestBody ReceiptDetails receiptDetails){
        return receiptDetailsService.saveReceiptDetails(receiptDetails);
    }

    @PutMapping("/public/update/receiptDetail")
    public ReceiptDetails updateReceiptDetails(@RequestBody ReceiptDetails receiptDetails){
        return receiptDetailsService.saveReceiptDetails(receiptDetails);
    }

    @DeleteMapping("/public/delete/receiptDetail/{receiptDetailId}")
    public void deleteReceipDetail(@PathVariable("receiptDetailId") Long receiptDetailId){
        receiptDetailsService.deleteReceiptDetails(receiptDetailId);
    }
}

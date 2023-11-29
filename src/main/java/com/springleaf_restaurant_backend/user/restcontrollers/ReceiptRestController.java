package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Receipt;
import com.springleaf_restaurant_backend.user.service.ReceiptService;

@RestController
public class ReceiptRestController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/public/receipts")
    public List<Receipt> receipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/public/receipt/{receiptId}")
    public Receipt receipt(@PathVariable("receiptId") Long receiptId) {
        return receiptService.getReceiptById(receiptId);
    }

    @PostMapping("/public/create/receipt")
    public Receipt createReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }

    @PutMapping("/public/update/receipt")
    public Receipt updateReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }

    @DeleteMapping("/public/delete/receipt/{receiptId}")
    public void deleteReceipt(@PathVariable("receiptId") Long receiptId) {
        receiptService.deleteReceipt(receiptId);
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.service.BillService;

@RestController
public class BillRestController {
    @Autowired
    private BillService billService;

    @GetMapping("/public/bills")
    public List<Bill> getBills() {
        return billService.getAllBills();
    }

    @GetMapping("/public/bill/{billId}")
    public Bill getOneBill(@PathVariable("billId") Long billId){
        return billService.getBillById(billId);
    }

    @PostMapping("/public/create/bill")
    public Bill createBill(@RequestBody Bill bill){
        return billService.saveBill(bill);
    }

    @PutMapping("/public/update/bill")
    public Bill updateBill(@RequestBody Bill bill){
        return billService.saveBill(bill);
    }

    @DeleteMapping("/public/delete/bill/{billId}")
    public void deleteBill(@PathVariable("billId") Long billId){
        billService.deleteBill(billId);
    }
}

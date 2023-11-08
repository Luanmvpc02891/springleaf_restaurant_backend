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

    @GetMapping("/bills")
    public List<Bill> getBills() {
        return billService.getAllBills();
    }
}

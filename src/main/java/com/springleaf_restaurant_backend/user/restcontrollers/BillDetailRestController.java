package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.service.BillDetailService;

@RestController
public class BillDetailRestController {
    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/api/billDetails")
    public List<BillDetail> getBilletails() {
        return billDetailService.getAllBillDetails();
    }
}

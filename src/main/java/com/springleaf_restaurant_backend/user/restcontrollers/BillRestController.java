package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.repositories.BillRepository;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/bills")
    public List<Bill> getBills() {
        return billRepository.findAll();
    }
}

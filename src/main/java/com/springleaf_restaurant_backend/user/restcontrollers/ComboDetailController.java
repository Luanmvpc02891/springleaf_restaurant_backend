package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.ComboDetail;
import com.springleaf_restaurant_backend.user.repositories.ComboDetailRepository;

@RestController
@RequestMapping("/api")
public class ComboDetailController {
    @Autowired
    private ComboDetailRepository comboDetailRepository;

    @GetMapping("/comboDetails")
    public List<ComboDetail> getComnComboDetails() {
        return comboDetailRepository.findAll();
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.ComboDetail;
import com.springleaf_restaurant_backend.user.service.ComboDetailService;


@RestController
public class ComboDetailController {
    @Autowired
    private ComboDetailService comboDetailService;

    @GetMapping("/public/comboDetails")
    public List<ComboDetail> getComnComboDetails() {
        return comboDetailService.getAllComboDetails();
    }

    @GetMapping("/public/comboDetail/{comboDetailId}")
    public ComboDetail getComboDetailById(@PathVariable("comboDetailId") Long comboDetailId){
        return comboDetailService.getComboDetailById(comboDetailId);
    }

    @PostMapping("/public/create/comboDetail")
    public ComboDetail createComboDetail(@RequestBody ComboDetail comboDetail) {
        return comboDetailService.saveComboDetail(comboDetail);
    }

    @PutMapping("/public/update/comboDetail")
    public ComboDetail updateCategory(@RequestBody ComboDetail comboDetail) {
        if (comboDetailService.getComboDetailById(comboDetail.getComboDetailId()) != null) {
            return comboDetailService.saveComboDetail(comboDetail);
        } else {
            return null;
        }
    }
    
    @DeleteMapping("/public/delete/comboDetail/{comboDetailId}")
    public void deleteComboDetail(@PathVariable("comboDetailId") Long comboDetailId){
        comboDetailService.deleteComboDetail(comboDetailId);
    }

    
}

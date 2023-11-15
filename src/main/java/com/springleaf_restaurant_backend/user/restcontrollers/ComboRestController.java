package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Combo;
import com.springleaf_restaurant_backend.user.service.ComboService;

@RestController
public class ComboRestController {
    @Autowired
    private ComboService comboService;

    @GetMapping("/public/combos")
    public List<Combo> getCombos() {
        return comboService.getAllCombos();
    }

    @GetMapping("/public/combo/{comboId}")
    public Combo getCombos(@PathVariable("comboId") Long comboId) {
        return comboService.getComboById(comboId);
    }

    @PostMapping("/public/create/combo")
    public Combo createCombo(@RequestBody Combo combo) {
        return comboService.saveCombo(combo);
    }

    @PutMapping("/public/update/combo")
    public Combo updateCombo(@RequestBody Combo updated) {
        return comboService.saveCombo(updated);
    }

    @DeleteMapping("/public/delete/combo/{comboId}")
    public void deleteCombo(@PathVariable("comboId") Long comboId) {
        comboService.deleteCombo(comboId);
    }
}

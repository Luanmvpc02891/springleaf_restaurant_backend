package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Combo;
import com.springleaf_restaurant_backend.user.repositories.ComboRepository;

@RestController
@RequestMapping("/api")
public class ComboRestController {
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping("/combos")
    public List<Combo> getCombos() {
        return comboRepository.findAll();
    }

    @PostMapping("/combo")
    public Combo createCombo(@RequestBody Combo combo) {
        return comboRepository.save(combo);
    }

    @PutMapping("/combo/{comboId}")
    public Combo updateCombo(@PathVariable("comboId") Long comboId,
            @RequestBody Combo updated) {
        Optional<Combo> database = comboRepository.findById(comboId);
        if (database.isPresent()) {
            Combo existing = database.get();
            existing.setComboName(updated.getComboName());
            existing.setComboUser(updated.getComboUser());
            existing.setTotalAmount(updated.getTotalAmount());
            return comboRepository.save(existing);
        } else {
            return null;
        }
    }

    @DeleteMapping("/combo/{comboId}")
    public void deleteCombo(@PathVariable("comboId") Long comboId) {
        comboRepository.deleteById(comboId);
    }
}

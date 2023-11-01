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

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;

@RestController
@RequestMapping("/api")
public class IngredientRestController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/ingredient/{ingredientId}")
    public Optional<Ingredient> getIngredientById(@PathVariable("ingredientId") Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @PostMapping("/ingredient")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @PutMapping("/ingredient/{ingredientId}")
    public Ingredient updateIngredient(@PathVariable("ingredientId") Long ingredientId,
            @RequestBody Ingredient updatedIngredient) {
        Optional<Ingredient> databaseIngredient = ingredientRepository.findById(ingredientId);
        if (databaseIngredient.isPresent()) {
            Ingredient existingIngredient = databaseIngredient.get();
            existingIngredient.setIngredientId(updatedIngredient.getIngredientId());
            existingIngredient.setName(updatedIngredient.getName());
            existingIngredient.setDescription(updatedIngredient.getDescription());
            existingIngredient.setOrderThreshold(updatedIngredient.getOrderThreshold());
            return ingredientRepository.save(existingIngredient);
        } else {
            return null;
        }
    }

    @DeleteMapping("/ingredient/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}

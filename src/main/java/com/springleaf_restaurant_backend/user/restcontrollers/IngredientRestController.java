package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.service.IngredientService;

@RestController
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/public/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/public/ingredient/{ingredientId}")
    public Ingredient getIngredientById(@PathVariable("ingredientId") Long ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }

    @PostMapping("/public/create/ingredient")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @PutMapping("/public/update/ingredient")
    public Ingredient updateIngredient(@RequestBody Ingredient updatedIngredient) {
        return ingredientService.saveIngredient(updatedIngredient);
    }

    @DeleteMapping("/public/delete/ingredient/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }
}

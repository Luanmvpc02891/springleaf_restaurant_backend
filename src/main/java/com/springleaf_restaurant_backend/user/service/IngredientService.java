package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.Ingredient;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(Long id);

    Ingredient saveIngredient(Ingredient ingredient);

    void deleteIngredient(Long id);

}

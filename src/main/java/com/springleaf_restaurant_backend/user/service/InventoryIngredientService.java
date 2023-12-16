package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;

public interface InventoryIngredientService {
  List<InventoryIngredient> getAll();

    InventoryIngredient getInventoryIngredientById(Long id);

    InventoryIngredient saveInventoryIngredient(InventoryIngredient inventoryIngredient);

    void deleteInventoryIngredient(Long id);

}

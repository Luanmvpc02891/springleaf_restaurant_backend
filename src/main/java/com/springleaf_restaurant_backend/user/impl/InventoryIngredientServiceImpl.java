package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;
import com.springleaf_restaurant_backend.user.repositories.InventoryIngredientRepository;
import com.springleaf_restaurant_backend.user.service.InventoryIngredientService;

@Service
public class InventoryIngredientServiceImpl implements InventoryIngredientService {
  private final InventoryIngredientRepository inventoryIngredientService;

  public InventoryIngredientServiceImpl(InventoryIngredientRepository inventoryIngredientRepository) {
    this.inventoryIngredientService = inventoryIngredientRepository;
  }

  @Override
  public List<InventoryIngredient> getAll() {
    return inventoryIngredientService.findAll();
  }

  @Override
  public InventoryIngredient getInventoryIngredientById(Long id) {
    return inventoryIngredientService.findById(id).orElse(null);
  }

  @Override
  public InventoryIngredient saveInventoryIngredient(InventoryIngredient inventoryIngredient) {
    return inventoryIngredientService.save(inventoryIngredient);
  }

  @Override
  public void deleteInventoryIngredient(Long id) {
    inventoryIngredientService.deleteById(id);
  }
}

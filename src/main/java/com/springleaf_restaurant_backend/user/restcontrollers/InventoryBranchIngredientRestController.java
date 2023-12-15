package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.service.InventoryBranchIngredientService;

@RestController
public class InventoryBranchIngredientRestController {
  @Autowired
  private InventoryBranchIngredientService inventoryBranchIngredientService;

  @GetMapping("/public/InventoryBranchIngredients")
  public List<InventoryBranchIngredient> getInventoryBranchIngredients() {
    return inventoryBranchIngredientService.getAll();
  }

  @PostMapping("/public/create/InventoryBranchIngredient")
  public InventoryBranchIngredient postMethodName(@RequestBody InventoryBranchIngredient inventoryBranchIngredient) {
    return inventoryBranchIngredientService.saveInventoryBranchIngredient(inventoryBranchIngredient);
  }
}

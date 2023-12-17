package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.InventoryIngredient;
import com.springleaf_restaurant_backend.user.service.InventoryIngredientService;

@RestController

public class InventoryIngredientRestController {
  @Autowired
  private InventoryIngredientService inventoryIngredientService;

  @GetMapping("/public/inventoryIngredients")
  public List<InventoryIngredient> getInventoryIngredient() {
    return inventoryIngredientService.getAll();
  }

  @PostMapping("/public/create/inventoryIngredient")
  public InventoryIngredient postMethodName(@RequestBody InventoryIngredient inventoryIngredient) {
    return inventoryIngredientService.saveInventoryIngredient(inventoryIngredient);
  }
  

  @DeleteMapping("/public/delete/inventoryIngredient/{inventoryIngredientId}")
  public void deleteIinventoryIngredient(@PathVariable("inventoryIngredientId") Long inventoryIngredientId) {
    inventoryIngredientService.deleteInventoryIngredient(inventoryIngredientId);
  }

}

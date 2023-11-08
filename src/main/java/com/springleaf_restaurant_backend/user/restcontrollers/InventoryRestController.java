package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Inventory;
import com.springleaf_restaurant_backend.user.service.InventoryService;

@RestController
public class InventoryRestController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/public/inventories")
    public List<Inventory> getInventoryInfo() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/public/inventory/{inventoryId}")
    public Inventory getOneInventoryInfo(@PathVariable("invetoryId") Long invetoryId) {
        return inventoryService.getInventoryById(invetoryId);
    }

    @PostMapping("/public/create/inventory")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("/public/update/inventory")
    public Inventory updateInventory(@RequestBody Inventory updatedInventory) {
        if (inventoryService.getInventoryById(updatedInventory.getInventoryId()) != null) {
            return inventoryService.saveInventory(updatedInventory);
        } else {
            return null;
        }
    }

    @DeleteMapping("/public/delete/inventory/{invetoryId}")
    public void deleteInventory(@PathVariable("invetoryId") Long invetoryId) {
        inventoryService.deleteInventory(invetoryId);
    }
}

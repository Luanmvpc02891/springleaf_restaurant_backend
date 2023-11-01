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

import com.springleaf_restaurant_backend.user.entities.Inventory;
import com.springleaf_restaurant_backend.user.repositories.InventoryRepository;

@RestController
@RequestMapping("/api")
public class InventoryRestController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventories")
    public List<Inventory> getInventoryInfo() {
        // return inventoryRepository.getInventoryInfo();
        return inventoryRepository.findAll();
    }

    @PostMapping("/inventory")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("/inventory/{inventoryId}")
    public Inventory updateInventory(@PathVariable("inventoryId") Long inventoryId,
            @RequestBody Inventory updatedInventory) {
        Optional<Inventory> database = inventoryRepository.findById(inventoryId);
        if (database.isPresent()) {
            Inventory existing = database.get();
            existing.setInventoryId(updatedInventory.getInventoryId());
            existing.setIngredientId(updatedInventory.getIngredientId());
            existing.setSupplierId(updatedInventory.getSupplierId());
            return inventoryRepository.save(existing);
        } else {
            return null;
        }
    }

    @DeleteMapping("/inventory/{invetoryId}")
    public void deleteInventory(@PathVariable("invetoryId") Long invetoryId) {
        inventoryRepository.deleteById(invetoryId);
    }
}

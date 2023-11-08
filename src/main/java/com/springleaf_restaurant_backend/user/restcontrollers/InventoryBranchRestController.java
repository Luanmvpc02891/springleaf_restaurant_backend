package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.service.InventoryBranchService;

@RestController
public class InventoryBranchRestController {
    @Autowired
    private InventoryBranchService inventoryBranchService;

    @GetMapping("/public/inventoryBranches")
    public List<InventoryBranch> getInventoryBranches() {
        return inventoryBranchService.getAllInventoryBranches();
    }

    @GetMapping("/public/inventoryBranches/{inventoryBranchId}")
    public InventoryBranch getInventoryBranchById(@PathVariable("inventoryBranchId") Long inventoryBranchId) {
        return inventoryBranchService.getInventoryBranchById(inventoryBranchId);
    }

    @PostMapping("/public/create/inventoryBranch")
    public InventoryBranch postMethodName(@RequestBody InventoryBranch inventoryBranch) {
        return inventoryBranchService.saveInventoryBranch(inventoryBranch);
    }

    @PutMapping("/public/update/inventoryBranch")
    public InventoryBranch putMethodName(@RequestBody InventoryBranch inventoryBranch) {
        if(inventoryBranchService.getInventoryBranchById(inventoryBranch.getInventoryBranchId()) != null){
            return inventoryBranchService.saveInventoryBranch(inventoryBranch);
        }else{
            return null;
        }
    }
    
    @DeleteMapping("/public/delete/inventoryBranch/{inventoryBranchId}")
    public void deleteInventoryBranchById(@PathVariable("inventoryBranchId") Long inventoryBranchId) {
        inventoryBranchService.deleteInventoryBranch(inventoryBranchId);
    }

}

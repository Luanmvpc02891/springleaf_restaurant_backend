package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;

@RestController
@RequestMapping("/api")
public class InventoryBranchRestController {
    @Autowired
    private InventoryBranchRepository inventoryBranchRepository;

    @GetMapping("/inventoryBranches")
    public List<InventoryBranch> getInventoryBranches() {
        return inventoryBranchRepository.findAll();
    }
}

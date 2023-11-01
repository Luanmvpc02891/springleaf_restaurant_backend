package com.springleaf_restaurant_backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;

@Service
public class InventoryBranchService {
    @Autowired
    InventoryBranchRepository inventoryBrandRepository;
}

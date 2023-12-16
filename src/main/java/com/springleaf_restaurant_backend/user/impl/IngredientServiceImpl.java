package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchIngredientRepository;
import com.springleaf_restaurant_backend.user.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final InventoryBranchIngredientRepository inventoryBranchIngredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
            InventoryBranchIngredientRepository inventoryBranchIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.inventoryBranchIngredientRepository = inventoryBranchIngredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

}

package com.springleaf_restaurant_backend.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.MenuItemIngredientRepository;
import com.springleaf_restaurant_backend.user.service.MenuItemIngredientService;

@Service
public class MenuItemIngredientServiceImpl implements MenuItemIngredientService {
    private final MenuItemIngredientRepository menuItemIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public MenuItemIngredientServiceImpl(MenuItemIngredientRepository menuItemIngredientRepository,
            IngredientRepository ingredientRepository) {
        this.menuItemIngredientRepository = menuItemIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<MenuItemIngredient> getAllMenuItemIngredients() {
        return menuItemIngredientRepository.findAll();
    }

    @Override
    public MenuItemIngredient getMenuItemIngredientById(Long id) {
        return menuItemIngredientRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItemIngredient saveMenuItemIngredient(MenuItemIngredient menuItemIngredient) {
        return menuItemIngredientRepository.save(menuItemIngredient);
    }

    @Override
    public void deleteMenuItemIngredient(Long id) {
        menuItemIngredientRepository.deleteById(id);
    }

    @Override
    public List<MenuItemIngredient> getIngredientsForMenuItem(Long menuItemId) {
        return menuItemIngredientRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public void decreaseIngredientOrderThreshold(Long ingredientId, Double quantity) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);

        optionalIngredient.ifPresent(ingredient -> {
            int currentOrderThreshold = ingredient.getOrderThreshold();
            int decreaseAmount = (int) Math.ceil(quantity); // Làm tròn lên để giảm theo số lượng nguyên liệu cần

            if (currentOrderThreshold >= decreaseAmount) {
                ingredient.setOrderThreshold(currentOrderThreshold - decreaseAmount);
                ingredientRepository.save(ingredient);
            }
        });
    }
}

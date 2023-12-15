package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;

import java.util.List;

public interface MenuItemIngredientService {
    List<MenuItemIngredient> getAllMenuItemIngredients();

    MenuItemIngredient getMenuItemIngredientById(Long id);

    MenuItemIngredient saveMenuItemIngredient(MenuItemIngredient menuItemIngredient);

    void deleteMenuItemIngredient(Long id);

    List<MenuItemIngredient> getIngredientsForMenuItem(Long menuItemId);

    void decreaseIngredientOrderThreshold(Long ingredientId, Double quantity);
}

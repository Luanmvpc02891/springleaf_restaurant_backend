package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;
import com.springleaf_restaurant_backend.user.repositories.MenuItemIngredientRepository;
import com.springleaf_restaurant_backend.user.service.MenuItemIngredientService;

import java.util.List;

@Service
public class MenuItemIngredientServiceImpl implements MenuItemIngredientService {
    private final MenuItemIngredientRepository menuItemIngredientRepository;

    @Autowired
    public MenuItemIngredientServiceImpl(MenuItemIngredientRepository menuItemIngredientRepository) {
        this.menuItemIngredientRepository = menuItemIngredientRepository;
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
    public MenuItemIngredient updateMenuItemIngredient(MenuItemIngredient menuItemIngredient) {
        return menuItemIngredientRepository.save(menuItemIngredient);
    }

    @Override
    public void deleteMenuItemIngredient(Long id) {
        menuItemIngredientRepository.deleteById(id);
    }
}

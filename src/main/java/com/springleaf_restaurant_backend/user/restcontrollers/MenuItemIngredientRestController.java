package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;
import com.springleaf_restaurant_backend.user.repositories.MenuItemIngredientRepository;

@RestController
@RequestMapping("/api")
public class MenuItemIngredientRestController {
    @Autowired
    private MenuItemIngredientRepository menuItemIngredientRepository;

    @GetMapping("/menuItemIngredients")
    public List<MenuItemIngredient> getCategories() {
        return menuItemIngredientRepository.findAll();
    }
}

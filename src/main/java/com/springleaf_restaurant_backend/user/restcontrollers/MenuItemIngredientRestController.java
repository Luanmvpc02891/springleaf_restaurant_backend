package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;
import com.springleaf_restaurant_backend.user.service.MenuItemIngredientService;
@RestController
public class MenuItemIngredientRestController {
    @Autowired
    private MenuItemIngredientService menuItemIngredientService;

    @GetMapping("/public/menuItemIngredients")
    public List<MenuItemIngredient> getMenuItemIngredients() {
        return menuItemIngredientService.getAllMenuItemIngredients();
    }

    @GetMapping("/public/menuItemIngredient/{menuItemIngredientsId}")
    public MenuItemIngredient getOneMenuItemIngredient(@PathVariable("menuItemIngredientsId") Long menuItemIngredientsId){
        return menuItemIngredientService.getMenuItemIngredientById(menuItemIngredientsId);
    }

    @PostMapping("/public/create/menuItemIngredient")
    public MenuItemIngredient createMenuItemIngredient(@RequestBody MenuItemIngredient menuItemIngredient){
        return menuItemIngredientService.saveMenuItemIngredient(menuItemIngredient);
    }

    @PutMapping("/public/update/menuItemIngredient")
    public MenuItemIngredient putMethodName(@RequestBody MenuItemIngredient menuItemIngredient) {
        return menuItemIngredientService.saveMenuItemIngredient(menuItemIngredient);
    }

    @DeleteMapping("/public/delete/menuItemIngredient/{menuItemIngredientId}")
    public void deleteMenuItemInfredient(@PathVariable("menuItemIngredientId") Long menuItemIngredientId){
        menuItemIngredientService.deleteMenuItemIngredient(menuItemIngredientId);
    }
}

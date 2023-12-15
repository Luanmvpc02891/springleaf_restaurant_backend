package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public MenuItemIngredient getOneMenuItemIngredient(
            @PathVariable("menuItemIngredientsId") Long menuItemIngredientsId) {
        return menuItemIngredientService.getMenuItemIngredientById(menuItemIngredientsId);
    }

    @PostMapping("/public/create/menuItemIngredient")
    public MenuItemIngredient createMenuItemIngredient(@RequestBody MenuItemIngredient menuItemIngredient) {
        // return menuItemIngredientService.saveMenuItemIngredient(menuItemIngredient);
        MenuItemIngredient savedMenuItemIngredient = menuItemIngredientService
                .saveMenuItemIngredient(menuItemIngredient);

        // Giảm orderThreshold của ingredient tương ứng dựa trên số lượng của
        // menuItemIngredient
        Long ingredientId = savedMenuItemIngredient.getIngredientId();
        Double quantity = savedMenuItemIngredient.getQuantity();
        menuItemIngredientService.decreaseIngredientOrderThreshold(ingredientId, quantity);

        return savedMenuItemIngredient;
    }

    @PutMapping("/public/update/menuItemIngredient")
    public MenuItemIngredient putMethodName(@RequestBody MenuItemIngredient menuItemIngredient) {
        return menuItemIngredientService.saveMenuItemIngredient(menuItemIngredient);
    }

    @DeleteMapping("/public/delete/menuItemIngredient/{menuItemIngredientId}")
    public void deleteMenuItemInfredient(@PathVariable("menuItemIngredientId") Long menuItemIngredientId) {
        menuItemIngredientService.deleteMenuItemIngredient(menuItemIngredientId);
    }

    @GetMapping("/public/{menuItemId}/ingredients")
    public ResponseEntity<List<MenuItemIngredient>> getIngredientsForMenuItem(@PathVariable Long menuItemId) {
        List<MenuItemIngredient> ingredients = menuItemIngredientService.getIngredientsForMenuItem(menuItemId);
        if (ingredients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }
}

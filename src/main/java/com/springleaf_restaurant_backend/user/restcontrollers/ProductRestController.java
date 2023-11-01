package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.repositories.MenuItemRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    MenuItemRepository menuItemRepository;

    @GetMapping("/products")
    public List<MenuItem> getCategories() {
        return menuItemRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<MenuItem> getProductById(@PathVariable("id") Long productId) {
        return menuItemRepository.findById(productId);
    }

    @GetMapping("/category/{id}/products")
    public List<MenuItem> getProductByCategoryId(@PathVariable("id") Long categoryId) {
        List<MenuItem> menuItems = menuItemRepository.findByCategoryId(categoryId);
        return menuItems;
    }

    @PostMapping("/product")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @PutMapping("/product/{menuItemId}")
    public MenuItem updateMenuItem(@PathVariable("menuItemId") Long menuItemId,
            @RequestBody MenuItem updatedMenuItem) {
        Optional<MenuItem> databaseMenuItem = menuItemRepository.findById(menuItemId);
        if (databaseMenuItem.isPresent()) {
            MenuItem existingMenuItem = databaseMenuItem.get();
            existingMenuItem.setMenuItemId(updatedMenuItem.getMenuItemId());
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setImageUrl(updatedMenuItem.getImageUrl());
            existingMenuItem.setCategoryId(updatedMenuItem.getCategoryId());
            existingMenuItem.setStatus(updatedMenuItem.getStatus());
            return menuItemRepository.save(existingMenuItem);
        } else {
            return null;
        }
    }

    @DeleteMapping("/product/{menuItemId}")
    public void deleteProduct(@PathVariable("menuItemId") Long menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }
}

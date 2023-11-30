package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.service.CategoryService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;
import com.springleaf_restaurant_backend.user.service.MenuItemService;
import com.springleaf_restaurant_backend.user.service.OrderService;

@RestController
public class ProductRestController {

    @Autowired
    MenuItemService menuItemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DeliveryOrderService deliveryOrderService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("/public/products")
    public List<MenuItem> getCategories() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/public/products/{id}")
    public MenuItem getProductById(@PathVariable("id") Long productId) {
        return menuItemService.getMenuItemById(productId);
    }

    @GetMapping("/public/category/{id}/products")
    public List<MenuItem> getProductByCategoryId(@PathVariable("id") Category categoryId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByCategoryId(categoryId);
        return menuItems;
    }

    @PostMapping("/public/create/product")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.saveMenuItem(menuItem);
    }

    @PutMapping("/public/update/product")
    public MenuItem updateMenuItem(@RequestBody MenuItem updatedMenuItem) {
        return menuItemService.saveMenuItem(updatedMenuItem);
    }

    @DeleteMapping("/public/delete/product/{menuItemId}")
    public void deleteProduct(@PathVariable("menuItemId") Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
    }

}

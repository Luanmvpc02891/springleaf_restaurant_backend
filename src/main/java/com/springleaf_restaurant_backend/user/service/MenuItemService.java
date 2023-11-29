package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.entities.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAllMenuItems();

    List<MenuItem> getMenuItemsByCategoryId(Category categoryId);

    MenuItem getMenuItemById(Long id);

    MenuItem saveMenuItem(MenuItem menuItem);

    void deleteMenuItem(Long id);
}

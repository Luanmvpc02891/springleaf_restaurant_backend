package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category saveCategory(Category category);

    void deleteCategory(Long id);
}

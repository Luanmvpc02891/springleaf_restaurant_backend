package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.repositories.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @PostMapping("/category")
    public Category getCategoryById(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/category/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @PutMapping("/category/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category updatedCategory) {
        Optional<Category> databaseCategory = categoryRepository.findById(categoryId);
        if (databaseCategory.isPresent()) {
            Category existingCategory = databaseCategory.get();
            existingCategory.setCategoryId(updatedCategory.getCategoryId());
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setActive(updatedCategory.getActive());
            existingCategory.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(existingCategory);
        } else {
            return null;
        }
    }

}

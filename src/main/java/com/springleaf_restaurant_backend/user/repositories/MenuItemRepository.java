package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.Category;
import com.springleaf_restaurant_backend.user.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryId(Category categoryId);
    List<MenuItem> findByCategoryId(Long categoryId);
}

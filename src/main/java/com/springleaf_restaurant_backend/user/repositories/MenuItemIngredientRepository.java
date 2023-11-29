package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.MenuItemIngredient;

public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, Long> {
  List<MenuItemIngredient> findByMenuItemId(Long menuItemId);
}

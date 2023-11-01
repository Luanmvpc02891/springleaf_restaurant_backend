package com.springleaf_restaurant_backend.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springleaf_restaurant_backend.user.entities.Category;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

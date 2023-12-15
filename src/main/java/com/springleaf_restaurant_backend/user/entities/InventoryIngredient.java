package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_Ingredients")
public class InventoryIngredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_ingredient_id")
  private Long inventoryIngredientId;

  @Column(name = "ingredient_id")
  private Long ingredientId;

  @Column(name = "inventory_id")
  private Long inventoryId;

  @Column(name = "quantity")
  private Double quantity;
}

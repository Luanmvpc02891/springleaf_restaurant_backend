package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Menu_Item_Ingredients")
public class MenuItemIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_ingredient_id")
    private Long menuItemIngredientId;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "ingredient_id")
    private Long ingredient;

    @Column(name = "quantity")
    private Double quantity;

}

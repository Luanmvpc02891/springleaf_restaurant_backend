package com.springleaf_restaurant_backend.user.entities;

import lombok.*;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Menu_Items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "status")
    private Boolean status;

}

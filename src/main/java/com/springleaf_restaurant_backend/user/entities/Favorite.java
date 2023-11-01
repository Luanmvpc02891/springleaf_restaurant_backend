package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long favoriteId;

    @Column(name = "user_name")
    private Long user;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "favorite_date")
    private Date favoriteDate;

}

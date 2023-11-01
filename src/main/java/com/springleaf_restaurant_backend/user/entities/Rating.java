package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rates")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "rating")
    private Integer rating;

}

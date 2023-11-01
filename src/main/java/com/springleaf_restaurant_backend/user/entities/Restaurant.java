package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "address")
    private Integer address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

}

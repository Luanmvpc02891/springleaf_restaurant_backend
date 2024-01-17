package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Restaurant_Images")
public class RestaurantImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "image_name")
    String imageName;

    @Column(name = "restaurant_id")
    Long restaurantId;

    @Column(name = "table_id")
    Long tableId;

}

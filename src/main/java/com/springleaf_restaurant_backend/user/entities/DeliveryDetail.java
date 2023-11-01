package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Delivery_Details")
public class DeliveryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_detail_id")
    private Long deliveryDetailId;

    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "quantity")
    private Integer quantity;

}

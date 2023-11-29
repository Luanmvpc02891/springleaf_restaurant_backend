package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Thresholds")
public class OrderThreshold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_threshold_id")
    private Long orderThresholdId;

    @Column(name = "reorder_point")
    private Integer reorderPoint;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "inventory_branch_id")
    private Long inventoryBranchId;

    @Column(name = "inventory_id")
    private Long inventoryId;
}

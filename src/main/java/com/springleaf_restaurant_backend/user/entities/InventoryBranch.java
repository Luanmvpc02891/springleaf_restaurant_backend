package com.springleaf_restaurant_backend.user.entities;

import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Inventory_Branchs")
public class InventoryBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_branch_id")
    private Long inventoryBranchId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

}

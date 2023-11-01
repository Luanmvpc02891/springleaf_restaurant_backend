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

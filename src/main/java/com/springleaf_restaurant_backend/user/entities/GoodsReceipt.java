package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Goods_Receipt")
public class GoodsReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_receipt_id")
    private Long goodsReceiptId;

    @Column(name = "inventories_branch_id")
    private Long inventoryBranchId;

    @Column(name = "date")
    private String date;

    @Column(name = "inventory_manager_id")
    private Long warehouseManagerId;

    @Column(name = "inventory_branch_manager_id")
    private Long inventoryBranchManagerId;

}

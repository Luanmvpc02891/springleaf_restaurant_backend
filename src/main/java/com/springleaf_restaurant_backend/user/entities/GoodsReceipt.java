package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

import com.springleaf_restaurant_backend.security.entities.User;

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

    @Column(name = "inventories_brand_id")
    private Long inventoryBrand;

    @Column(name = "date")
    private Date date;

    @Column(name = "warehouse_manager_id")
    private Long warehouseManager;

    @Column(name = "user_id")
    private Long user;

}

package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GoodsReceipt_Details")
public class GoodsReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goodsreceipt_detail_id")
    private Long goodsReceiptDetailId;

    @Column(name = "goods_receipt_id")
    private Long goodsReceiptId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "quantity")
    private Integer quantity;

}
 
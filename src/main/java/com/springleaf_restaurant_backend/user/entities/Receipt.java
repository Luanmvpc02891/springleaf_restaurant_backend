package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long receiptId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "date")
    private String date;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "inventory_id")
    private Long inventoryId;
}

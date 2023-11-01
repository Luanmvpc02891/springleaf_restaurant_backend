package com.springleaf_restaurant_backend.user.entities;

import lombok.*;

import java.util.Date;

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
    private String userId;

    @Column(name = "supplier_id")
    private Long supplier;

    @Column(name = "date")
    private Date date;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "inventory_id")
    private Long inventory;

}

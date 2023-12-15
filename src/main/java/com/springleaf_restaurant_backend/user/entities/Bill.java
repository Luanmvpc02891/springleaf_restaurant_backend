package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name = "Bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "bill_time")
    private String billTime;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "payment_method")
    private Long paymentMethod;

    @Column(name = "address")
    private Integer address;

    @Column(name = "bank_number")
    private String bankNumber;

}

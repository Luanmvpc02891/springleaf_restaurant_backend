package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

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

    @Column(name = "user_name")
    private Long userName;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "bill_time")
    private Date billTime;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "payment_method")
    private Long paymentMethod;

    @Column(name = "address")
    private Integer address;

    @Column(name = "bank_number")
    private String bankNumber;

    // Constructors, getters, and setters
}

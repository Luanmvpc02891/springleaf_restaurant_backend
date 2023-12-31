package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "combo_id")
    private Long combo;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "delivery_order_id")
    private Long deliveryOrderId;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "status")
    private boolean status;
}

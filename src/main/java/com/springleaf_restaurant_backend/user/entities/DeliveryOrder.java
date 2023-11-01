package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Delivery_Orders")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_order_id")
    private Long deliveryOrderId;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_address", columnDefinition = "text")
    private String deliveryAddress;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "status_id")
    private Long status;

}

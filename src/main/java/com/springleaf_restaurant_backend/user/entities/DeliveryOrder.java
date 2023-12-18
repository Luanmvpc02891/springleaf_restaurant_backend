package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;
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

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "delivery_restaurant_id")
    private Long deliveryRestaurantId;

    @Column(name = "delivery_order_type_id")
    private Integer deliveryOrderTypeId;

    @Column(name = "delivery_order_status_id")
    private Integer deliveryOrderStatusId;

    @Column(name = "active")
    private boolean active;
}
 
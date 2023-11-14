package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Delivery_Orders_Status")
public class DeliveryOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_order_status_id")
    private Long deliveryOrderStatusId;

    @Column(name = "delivery_order_status_name")
    private String deliveryOrderStatusName;
}

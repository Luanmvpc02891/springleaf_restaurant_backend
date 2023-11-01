package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Delivery_Order_Details")
public class DeliveryOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_oder_detail_id")
    private Long deliveryOrderDetailId;

    @Column(name = "deliveryoder_id")
    private Long deliveryOrder;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "order_time")
    private Date orderTime;

}

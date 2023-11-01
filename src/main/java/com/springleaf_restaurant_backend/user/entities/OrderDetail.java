package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "menu_item_id")
    private Long menuItem;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

}

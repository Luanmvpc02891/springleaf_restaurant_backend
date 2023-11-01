package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "table_id")
    private Long table;

    @Column(name = "order_type")
    private Long orderType;

    @Column(name = "combo_id")
    private Long combo;

}

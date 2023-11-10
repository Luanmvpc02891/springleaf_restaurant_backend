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

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservationId;

    @OneToOne
    @JoinColumn(name = "delivery_order_id")
    private DeliveryOrder deliveryOrderId;

    @Column(name = "status")
    private boolean status;

}

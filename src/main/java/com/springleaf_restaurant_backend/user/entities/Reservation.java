package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "restaurant_table_id")
    private Long restaurantTableId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_date")
    private String reservationDate;

    @Column(name = "out_time")
    private String outTime;

    @Column(name = "number_of_guest")
    private Long numberOfGuests;

    @Column(name = "reservation_status_name")
    private String reservationStatusName;

    @Column(name = "reservation_order_status")
    private Boolean reservationOrderStatus;

    @Column(name = "username")
    private String username;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "reservation_deposit")
    private Double reservationDeposit;

}

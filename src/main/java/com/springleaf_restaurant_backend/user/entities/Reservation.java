package com.springleaf_restaurant_backend.user.entities;

import lombok.*;

import java.util.Date;

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

    @Column(name = "table_id")
    private Long table;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "user_name")
    private Long user;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "number_of_guest")
    private Long numberOfGuests;

}

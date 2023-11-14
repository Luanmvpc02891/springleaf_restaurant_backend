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

    @Column(name = "restaurant_table_id")
    private Long restaurantTableId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "number_of_guest")
    private Long numberOfGuests;
}

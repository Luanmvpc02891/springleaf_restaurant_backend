package com.springleaf_restaurant_backend.user.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "number_of_guest")
    private Integer numberOfGuests;

    @Column(name = "combo_id")
    private Long combo;

    @Column(name = "order_id")
    private Long order;

}

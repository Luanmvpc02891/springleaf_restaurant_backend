package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.repositories.ReservationRepository;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }
}

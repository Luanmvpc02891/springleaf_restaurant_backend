package com.springleaf_restaurant_backend.user.service;

import java.time.LocalDate;
import java.util.List;

import com.springleaf_restaurant_backend.user.entities.Reservation;

public interface ReservationService {
    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    Reservation saveReservation(Reservation reservation);

    void deleteReservation(Long id);

    List<Reservation> getReservationsByUserId(Long userId);

    void saveAllReservations(List<Reservation> reservations);

    List<Reservation> getReservationsByDateList(String date);
}

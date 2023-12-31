package com.springleaf_restaurant_backend.user.impl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.repositories.ReservationRepository;
import com.springleaf_restaurant_backend.user.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void saveAllReservations(List<Reservation> reservations) {
        reservationRepository.saveAll(reservations);
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> getReservationsByDateList(String date) {
        return reservationRepository.findByReservationDateList(date);
    }

  
}

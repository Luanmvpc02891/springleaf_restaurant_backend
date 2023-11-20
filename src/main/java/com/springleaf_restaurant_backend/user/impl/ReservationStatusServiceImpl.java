package com.springleaf_restaurant_backend.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.ReservationStatus;
import com.springleaf_restaurant_backend.user.repositories.ReservationStatusRepository;
import com.springleaf_restaurant_backend.user.service.ReservationStatusService;

@Service
public class ReservationStatusServiceImpl implements ReservationStatusService {

    private final ReservationStatusRepository reservationStatusRepository;

    public ReservationStatusServiceImpl(ReservationStatusRepository reservationStatusRepository){
        this.reservationStatusRepository = reservationStatusRepository;
    }

    @Override
    public List<ReservationStatus> getAllReservationStatuses() {
        return reservationStatusRepository.findAll();
    }

    @Override
    public ReservationStatus getReservationStatusById(Integer id) {
        return reservationStatusRepository.findById(id).orElse(null); 
    }

    @Override
    public ReservationStatus saveReservationStatus(ReservationStatus reservationStatus) {
        return reservationStatusRepository.save(reservationStatus);
    }

    @Override
    public void deleteReservationStatus(Integer id) {
        reservationStatusRepository.deleteById(id);
    }
    
}

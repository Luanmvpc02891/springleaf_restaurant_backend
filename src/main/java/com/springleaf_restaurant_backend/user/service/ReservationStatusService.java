package com.springleaf_restaurant_backend.user.service;

import java.util.List;

import com.springleaf_restaurant_backend.user.entities.ReservationStatus;

public interface ReservationStatusService {

    List<ReservationStatus> getAllReservationStatuses();

    ReservationStatus getReservationStatusById(String id);

    ReservationStatus saveReservationStatus(ReservationStatus reservationStatus);

    void deleteReservationStatus(String id);
}

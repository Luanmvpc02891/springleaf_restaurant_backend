package com.springleaf_restaurant_backend.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springleaf_restaurant_backend.user.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  List<Reservation> findByUserId(Long userId);

  // List<Reservation> findByReservationDate(String date);
  @Query("SELECT r FROM Reservation r WHERE SUBSTRING(r.reservationDate, 1, 10) = :date")
  List<Reservation> findByReservationDateList(@Param("date") String date);
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.service.ReservationService;

@RestController
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/public/reservations")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/public/reservation/{reservationId}")
    public Reservation getReservation(@PathVariable("reservationId") Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @PostMapping("/public/create/reservation")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/public/update/reservation")
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.saveReservation(reservation);
    }
    @DeleteMapping("/public/delete/reservation/{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @Scheduled(fixedRate = 1000) // Chạy mỗi giây
public void updateReservationStatus() {
    List<Reservation> reservations = reservationService.getAllReservations();
    Instant currentDateTime = Instant.now();

    // Tạo danh sách để lưu trữ các bản ghi cần cập nhật
    List<Reservation> reservationsToUpdate = new ArrayList<>();

    for (Reservation reservation : reservations) {
        Instant reservationDateTime = Instant.parse(reservation.getReservationDate());

        // Bỏ qua nếu đã sử dụng xong
        if (reservation.getReservationStatusId() != null && reservation.getReservationStatusId() == 2) {
            continue;
        }

        // Kiểm tra nếu đã sử dụng xong và thời gian hiện tại >= reservationDate + 2 giờ
        if (reservation.getReservationStatusId() != null &&
            reservation.getReservationStatusId() == 1 &&
            currentDateTime.isAfter(reservationDateTime.plus(2, ChronoUnit.HOURS))) {
            reservation.setReservationStatusId(2); // Đã sử dụng xong
            reservationsToUpdate.add(reservation); // Thêm vào danh sách cần cập nhật
        }

        // Kiểm tra nếu thời gian hiện tại >= reservationDate
        if (currentDateTime.isAfter(reservationDateTime)) {
            reservation.setReservationStatusId(3); // Đang đợi
            reservationsToUpdate.add(reservation); // Thêm vào danh sách cần cập nhật
        }
    }

    // Lưu cập nhật vào cơ sở dữ liệu (nếu cần)
    if (!reservationsToUpdate.isEmpty()) {
        reservationService.saveAllReservations(reservationsToUpdate);
    }
}

}

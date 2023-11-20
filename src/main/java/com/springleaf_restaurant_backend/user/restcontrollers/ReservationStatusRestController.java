package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.ReservationStatus;
import com.springleaf_restaurant_backend.user.service.ReservationStatusService;

@RestController
public class ReservationStatusRestController {

    @Autowired
    private ReservationStatusService reservationStatusService;

    @GetMapping("/public/reservationStatuses")
    public List<ReservationStatus> getReservationStatus() {
        return reservationStatusService.getAllReservationStatuses();
    }

    @GetMapping("/public/reservationStatus/{reservationStatusId}")
    public ReservationStatus getRestaurantById(@PathVariable("reservationStatusId") Integer reservationStatusId) {
        return reservationStatusService.getReservationStatusById(reservationStatusId);
    }

    @PostMapping("/public/create/reservationStatus")
    public ReservationStatus createReservationStatus(@RequestBody ReservationStatus reservationStatus){
        return reservationStatusService.saveReservationStatus(reservationStatus);
    }

    @PutMapping("/public/update/reservationStatus")
    public ReservationStatus updateReservationStatus(@RequestBody ReservationStatus reservationStatus) {
        return reservationStatusService.saveReservationStatus(reservationStatus);
    }

    @DeleteMapping("/public/delete/reservationStatus/{reservationStatusId}")
    public void deleteReservationStatusById(@PathVariable("reservationStatusId") Integer reservationStatusId) {
        reservationStatusService.deleteReservationStatus(reservationStatusId);
    }

}

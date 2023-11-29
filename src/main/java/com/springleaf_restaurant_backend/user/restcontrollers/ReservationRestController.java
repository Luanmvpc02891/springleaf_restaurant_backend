package com.springleaf_restaurant_backend.user.restcontrollers;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.websocket.WebSocketMessage;
import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.service.ReservationService;

@RestController
public class ReservationRestController {

    private final SimpMessagingTemplate messagingTemplate;
    private List<Reservation> reservationsToUpdate = new ArrayList<>(); // De

    // Inject the SimpMessagingTemplate in the constructor
    public ReservationRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

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
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/public/update/reservation")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @DeleteMapping("/public/delete/reservation/{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping("public/reservations/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Long userId) {
        List<Reservation> userReservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(userReservations);
    }

    // Custom method

    private void sendToWebSocket(List<Reservation> reservations) {
        WebSocketMessage message = new WebSocketMessage();
        message.setName("reservations");
    
        // Chuyển đổi List thành mảng
        Object[] reservationArray = reservations.toArray();
    
        // Gán mảng vào đối tượng WebSocketMessage
        message.setObjects(reservationArray);
    
        // Sử dụng mảng chứa đối tượng khi gửi thông điệp
        messagingTemplate.convertAndSend("/public/greetings", message);
    }

    @Scheduled(fixedRate = 1000) // Run every second
    public void updateReservationStatusAndSend() {

        // System.out.println("Scheduled task started.");
        List<Reservation> reservations = reservationService.getAllReservations();
        updateReservationStatus(reservations);
        sendToWebSocket(reservations);
        System.out.println("WebSocket message sent.");
        // System.out.println("Scheduled task completed.");
    }

    private void updateReservationStatus(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Integer isCurrentBefore = checkDateTime(reservation.getReservationDate());
            if ("Đã sử dụng xong".equalsIgnoreCase(reservation.getReservationStatusName())) {
                continue;
            } else if ("Đã hủy".equalsIgnoreCase(reservation.getReservationStatusName())) {
                continue;
            } else if ("Đang chờ".equalsIgnoreCase(reservation.getReservationStatusName())) {
                if (isCurrentBefore == 1) {
                    continue;
                } else if (isCurrentBefore == 2) {
                    reservation.setReservationStatusName("Đang sử dụng");
                }
            } else if ("Đang sử dụng".equalsIgnoreCase(reservation.getReservationStatusName())) {
                if (isCurrentBefore == 2) {
                    continue;
                } else if (isCurrentBefore == 3) {
                    reservation.setReservationStatusName("Đã sử dụng xong");
                }
            }
        }
    }

    Integer checkDateTime(String reservationDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try {
            // Chuyển đổi chuỗi thành đối tượng LocalDateTime
            LocalDateTime dateTime1 = LocalDateTime.parse(reservationDateTime, formatter);

            // Lấy ngày giờ hiện tại
            LocalDateTime currentDateTime = LocalDateTime.now();

            // So sánh ngày giờ
            int comparisonResult = currentDateTime.compareTo(dateTime1);

            if (comparisonResult < 0) {
                // System.out.println("Ngày giờ hiện tại nằm trước ngày giờ tới.");
                return 1;
            } else if (comparisonResult == 0) {
                // System.out.println("Ngày giờ hiện tại và ngày giờ tới là giống nhau.");
                return 2;
            } else if (currentDateTime.isEqual(dateTime1.plusHours(2))
                    || currentDateTime.isAfter(dateTime1.plusHours(2))) {
                return 3;
                // System.out.println("Ngày giờ hiện tại nằm sau ngày giờ tới.");
            } else {
                return 2;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 3;

    }

}

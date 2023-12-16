package com.springleaf_restaurant_backend.user.restcontrollers;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.config.websocket.WebSocketMessage;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderType;
import com.springleaf_restaurant_backend.user.entities.MailInfo;
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;
import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderStatusService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderTypeService;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;
import com.springleaf_restaurant_backend.user.service.OrderService;
import com.springleaf_restaurant_backend.user.service.ReservationService;
import com.springleaf_restaurant_backend.user.service.mail.MailerService;

@RestController
public class ReservationRestController {

    private final SimpMessagingTemplate messagingTemplate;
    private List<Reservation> reservationsToUpdate = new ArrayList<>(); // De
    @Autowired
    MailerService mailerService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    DeliveryOrderService deliveryOrderService;
    @Autowired
    UserService userService;
    @Autowired
    DeliveryOrderStatusService deliveryOrderStatusService;
    @Autowired
    DeliveryOrderTypeService deliveryOrderTypeService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

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

    @PostMapping("/public/create/order/{reservationId}")
    public ResponseEntity<?> addMenuItem(
            @RequestHeader("Authorization") String jwtToken, // Người dùng
            @PathVariable("reservationId") Long reservationId, // bàn đặt
            @RequestBody List<MenuItem> listMenuItems) {
        MessageResponse message = new MessageResponse();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        Order orderUser = new Order();
        orderUser.setReservationId(reservationId);
        orderUser.setOrderDate(formattedDate);
        orderUser.setStatus(true);
        orderService.saveOrder(orderUser);

        List<OrderDetail> listDetail = orderDetailService.getAllOrderDetails();
        List<OrderDetail> listOrder = new ArrayList<>();
        for (OrderDetail detail : listDetail) {
            for (MenuItem item : listMenuItems) {
                if (detail.getMenuItemId() == item.getMenuItemId()) {
                    message.setMessage("Item was order");
                    return ResponseEntity.ok(message);
                } else {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setMenuItemId(item.getMenuItemId());
                    orderDetail.setOrderId(orderUser.getOrderId());
                    orderDetail.setQuantity(1);
                    listOrder.add(orderDetail);
                }
            }
        }
        for (OrderDetail detail : listOrder) {
            orderDetailService.saveOrderDetail(detail);
        }
        message.setMessage("Order success");
        System.out.println("ok");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/public/create/sendMail")
    public ResponseEntity<String> sendMail(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody String email) {
        String token = jwtToken.substring(7);
        Optional<User> user = userRepository.findByUsername(jwtService.extractUsername(token));
        if (user.isPresent()) {
            try {
                String emailTo = email;
                String subject = (new String("Mã xác nhận đăng ký".getBytes(StandardCharsets.UTF_8),
                        StandardCharsets.UTF_8));
                String body = ""; // Ngày giờ, nội dung muốn gửi
                mailerService.send(emailTo, subject, body, token);
                return ResponseEntity.ok("Email is sending");
            } catch (Exception e) {
                return ResponseEntity.ok("Email cant not send ! Please config email again");
            }
        } else {
            return ResponseEntity.ok("User with JWT not found");
        }
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
        messagingTemplate.convertAndSend("/public/" + "reservations", message);
    }

    @Scheduled(fixedRate = 10000) // Run every 10 second
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
            String resevationStatusName = reservation.getReservationStatusName();
            if ("Đã sử dụng xong".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Đã hủy".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Đang sử dụng".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Hết thời gian đợi".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Chưa tới".equalsIgnoreCase(resevationStatusName)) {
                if (isCurrentBefore == 1) {
                    continue;
                } else if (isCurrentBefore == 2) {
                    reservation.setReservationStatusName("Đang đợi");
                    updateReservation(reservation);
                }
            } else if ("Đang đợi".equalsIgnoreCase(resevationStatusName)) {
                if (isCurrentBefore == 3) {
                    reservation.setReservationStatusName("Hết thời gian đợi");
                    updateReservation(reservation);
                }
            }
        }
    }

    Integer checkDateTime(String reservationDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Chuyển đổi chuỗi thành đối tượng LocalDateTime
            LocalDateTime dateTime1 = LocalDateTime.parse(reservationDateTime, formatter);

            // Lấy ngày giờ hiện tại
            LocalDateTime currentDateTime = LocalDateTime.now();

            // LocalDateTime outTime1 = LocalDateTime.parse(outTime, formatter);

            // So sánh ngày giờ
            int comparisonResult = currentDateTime.compareTo(dateTime1);

            if (comparisonResult < 0) {
                // System.out.println("Ngày giờ hiện tại nằm trước ngày giờ tới.");
                return 1;
            } else if (comparisonResult == 0) {
                // System.out.println("Ngày giờ hiện tại và ngày giờ tới là giống nhau.");
                return 2; // Ngày giờ hiện tại và giờ tới là giống nhau
            } else if (currentDateTime.isAfter(dateTime1.plusMinutes(30))) { // nếu giờ hiện tại bằng hoặc vượt qua thời
                                                                             // gian đợi
                return 3; // Hết thời gian đợi
            } else {
                return 2; // Ngày giờ hiện tại lớn hơn thời gian đến
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 3;

    }

    @GetMapping("public/by-date")
    public List<Reservation> getReservationsByDate(
            @RequestParam("date") String date) {
        return reservationService.getReservationsByDateList(date);
    }

}

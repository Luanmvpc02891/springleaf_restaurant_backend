package com.springleaf_restaurant_backend.user.restcontrollers;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.springleaf_restaurant_backend.user.entities.MenuItem;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;
import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.entities.Reservation;
import com.springleaf_restaurant_backend.user.repositories.OrderDetailRepository;
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
        Reservation newReservation = reservationService.saveReservation(reservation);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        Order order = new Order();
        order.setReservationId(newReservation.getReservationId());
        order.setStatus(true);
        order.setOrderDate(formattedDate);
        orderService.saveOrder(order);
        return newReservation;
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
            @RequestBody List<OrderDetail> listMenuItems) {
        MessageResponse message = new MessageResponse();
        Date date = new Date();

        String jwt = jwtToken.substring(7);
        String userName = jwtService.extractUsername(jwt);
        Optional<User> user = userService.findByUsername(userName);
        if (user.isPresent()) {
            Order orderUser = new Order();
            Optional<Order> orderByReservation = orderService.getOrdersByReservationId(reservationId);
            if (orderByReservation.isPresent() && orderByReservation.get().isStatus()) {
                orderUser = orderByReservation.get();
            }
            List<OrderDetail> listDetail = orderDetailService.getOrderDetailsByOrderId(orderUser.getOrderId());
            if (listDetail.size() <= 0) {
                for (OrderDetail menuItem : listMenuItems) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setMenuItemId(menuItem.getMenuItemId());
                    orderDetail.setOrderId(orderUser.getOrderId());
                    orderDetail.setQuantity(menuItem.getQuantity());
                    orderDetailService.saveOrderDetail(orderDetail);
                }

                return null;
            } else {
                List<OrderDetail> list = listMenuItems;
                for (OrderDetail detail : listDetail) {
                    for (OrderDetail item : listMenuItems) {
                        // Nếu order Detail đã có
                        if (detail.getMenuItemId() == item.getMenuItemId()
                                && item.getQuantity() != detail.getQuantity()) {
                            detail.setQuantity(item.getQuantity());
                            orderDetailService.saveOrderDetail(detail);
                            list.remove(item);
                            break;
                        }
                    }
                }
                for (OrderDetail item : list) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setMenuItemId(item.getMenuItemId());
                    orderDetail.setOrderId(orderUser.getOrderId());
                    orderDetail.setQuantity(item.getQuantity());
                    orderDetailService.saveOrderDetail(orderDetail);
                }
                message.setMessage("Order success");
                System.out.println("ok");
                return ResponseEntity.ok(message);
            }

        } else {
            return null;
        }

    }

    @PostMapping("/public/create/getOrderByReservation")
    public ResponseEntity<List<OrderDetail>> getOrderByReservation(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody Long reservationId) {
        Optional<Order> order = orderService.getOrdersByReservationId(reservationId);
        if (order.isPresent()) {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(order.get().getOrderId());
            return ResponseEntity.ok(orderDetails);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/public/create/orderDetail/reservationOrder/{reservationId}")
    public String addOrderDetail(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody List<OrderDetail> listOrderDetail,
            @PathVariable("reservationId") Long reservationId
    ) {

        Optional<Order> order = orderService.getOrdersByReservationId(reservationId);
        if(order.isPresent()){
            order.get();
            for (OrderDetail orderDetail : listOrderDetail) {
                System.out.println(orderDetail.getMenuItemId());
                OrderDetail newOrderDetail = new OrderDetail();
                newOrderDetail.setMenuItemId(orderDetail.getMenuItemId());
                newOrderDetail.setOrderId(order.get().getOrderId());
                newOrderDetail.setQuantity(orderDetail.getQuantity());
                orderDetailService.saveOrderDetail(newOrderDetail);
            }
            return "Success";
        }

        return null;
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
            String outTime = reservation.getOutTime();
            Integer isCurrentBefore = checkDateTime(reservation.getReservationDate(), outTime);
            String resevationStatusName = reservation.getReservationStatusName();
            if ("Đã sử dụng xong".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Đã hủy".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Đang sử dụng".equalsIgnoreCase(resevationStatusName)) {
                if (isCurrentBefore == 4) {
                    continue;
                } else if (isCurrentBefore == 5) {
                    reservation.setReservationStatusName("Hết thời gian dùng");
                    updateReservation(reservation);
                }
            } else if ("Hết thời gian dùng".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Hết thời gian đợi".equalsIgnoreCase(resevationStatusName)) {
                continue;
            } else if ("Đang đợi".equalsIgnoreCase(resevationStatusName)
                    || "Chưa tới".equalsIgnoreCase(resevationStatusName)) {
                if (isCurrentBefore == 3) {
                    reservation.setReservationStatusName("Hết thời gian đợi");
                    updateReservation(reservation);
                } else if ("Chưa tới".equalsIgnoreCase(resevationStatusName)) {
                    if (isCurrentBefore == 1) {
                        continue;
                    } else if (isCurrentBefore == 2) {
                        reservation.setReservationStatusName("Đang đợi");
                        updateReservation(reservation);
                    }
                }
            }
        }
    }

    Integer checkDateTime(String reservationDateTime, String outTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Chuyển đổi chuỗi thành đối tượng LocalDateTime
            LocalDateTime dateTime1 = LocalDateTime.parse(reservationDateTime, formatter);

            // Lấy ngày giờ hiện tại
            LocalDateTime currentDateTime = LocalDateTime.now();

            if (!outTime.isEmpty()) {
                LocalDateTime outTime1 = LocalDateTime.parse(outTime, formatter);
                if (currentDateTime.compareTo(outTime1) < 0) {
                    return 4;
                } else {
                    return 5;
                }
            }

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

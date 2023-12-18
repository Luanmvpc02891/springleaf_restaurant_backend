package com.springleaf_restaurant_backend.user.restcontrollers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.config.websocket.WebSocketMessage;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrderType;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;
import com.springleaf_restaurant_backend.user.entities.Order;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderStatusService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderTypeService;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;
import com.springleaf_restaurant_backend.user.service.OrderService;

@RestController
public class DeliveryOrderRestController {
    private final SimpMessagingTemplate messagingTemplate;

    public DeliveryOrderRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Autowired
    DeliveryOrderService deliveryOrderService;
    @Autowired
    JwtService jwtService;
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

    @GetMapping("/public/deliveryOrders")
    public List<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @GetMapping("/manager/deliveryOrders")
    public List<DeliveryOrder> getManagerDeliveryOrders(
        @RequestHeader("Authorization") String jwtToken
    ) {
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @GetMapping("/public/deliveryOrder/{deliveryOrderId}")
    public DeliveryOrder getOneDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId) {
        return deliveryOrderService.getDeliveryOrderById(deliveryOrderId);
    }

    @PostMapping("/public/create/deliveryOrder")
    public DeliveryOrder createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }

    @PutMapping("/public/update/deliveryOrder")
    public DeliveryOrder updateDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }

    @DeleteMapping("/public/delete/deliveryOrder/{deliveryOrderId}")
    public void deleteDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId) {
        deliveryOrderService.deleteDeliveryOrder(deliveryOrderId);
    }

    // Load thông tin giỏ hàng user
    @GetMapping("/public/user/getCartByUser")
    public ResponseEntity<DeliveryOrder> getUserCart(
            @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));
        Optional<User> userByToken = userService.findByUsername(username);
        Optional<DeliveryOrderType> type = deliveryOrderTypeService.getDeliveryOrderTypeByName("Delivery Order");
        Optional<DeliveryOrder> cart = deliveryOrderService.getDeliveryOrdersByUserIdAndTypeAndActive(
                userByToken.get().getUserId(), type.get().getDeliveryOrderTypeId(), true);
        if (userByToken.isEmpty()) {
            return ResponseEntity.ok(null);
        } else if (type.isEmpty()) {
            return ResponseEntity.ok(null);
        } else if (cart.isPresent()) {
            // Sử dụng cart đang có trạng thái true
            return ResponseEntity.ok(cart.get());
        } else {
            // Khi không có cart nào true thì sẽ tạo mới
            DeliveryOrder newCart = new DeliveryOrder();
            newCart.setCustomerId(userByToken.get().getUserId());
            newCart.setDeliveryOrderTypeId(type.get().getDeliveryOrderTypeId());
            newCart.setActive(true);
            deliveryOrderService.saveDeliveryOrder(newCart);
            System.out.println("cart new");
            return ResponseEntity.ok(newCart);
        }
    }

    @PostMapping("/public/create/user/getOrderByUser")
    public ResponseEntity<Order> getOrderByUser(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody Long deliveryOrderId) {
        Optional<Order> order = orderService.getOrdersByDeliveryOrderId(deliveryOrderId);
        if (order.isPresent() && order.get().isStatus()) {
            return ResponseEntity.ok(order.get());
        } else {
            Order newOrder = new Order();
            newOrder.setDeliveryOrderId(deliveryOrderId);
            newOrder.setStatus(true);
            orderService.saveOrder(newOrder);
            return ResponseEntity.ok(newOrder);
        }
    }

    @PostMapping("/public/create/user/getOrderDetailByUser")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByUser(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody Long orderId) {
        List<OrderDetail> orderDetail = orderDetailService.getOrderDetailsByOrderId(orderId);
        if (orderDetail != null) {
            return ResponseEntity.ok(orderDetail);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/public/create/addToCart/{menuItemId}/{deliveryOrderId}/{orderId}")
    public ResponseEntity<?> addToCart(
            @RequestHeader("Authorization") String jwtToken, // Người dùng
            @PathVariable("menuItemId") Long menuItemId, // Sản phẩm
            @PathVariable("deliveryOrderId") Long deliveryOrderId, // Giỏ hàng
            @PathVariable("orderId") Long orderId // Order
    ) {
        MessageResponse message = new MessageResponse();
        // Kiểm tra người dùng theo jwt
        String username = jwtService.extractUsername(jwtToken.substring(7));
        Optional<User> userByToken = userService.findByUsername(username);
        if (userByToken.isEmpty()) {
            message.setMessage("User not found");
            return ResponseEntity.ok(message);
        }
        // Kiểm tra type mua mang đi
        Optional<DeliveryOrderType> type = deliveryOrderTypeService.getDeliveryOrderTypeByName("Delivery Order");
        if (type.isEmpty()) {
            message.setMessage("Type not found");
            return ResponseEntity.ok(message);
        }
        Order orderUser = orderService.getOrderById(orderId);
        if (orderUser == null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            orderUser = new Order();
            orderUser.setDeliveryOrderId(deliveryOrderId);
            orderUser.setOrderDate(formattedDate);
            orderUser.setCombo(null);
            orderUser.setReservationId(null);
            orderUser.setStatus(true);
            orderService.saveOrder(orderUser);
        } else {
            orderUser = orderService.getOrdersByDeliveryOrderId(deliveryOrderId).get();
        }
        List<OrderDetail> listOrderDetail = orderDetailService.getOrderDetailsByOrderId(orderId);
        for (OrderDetail orderDetail : listOrderDetail) {
            if (orderDetail.getMenuItemId() == menuItemId) {
                message.setMessage("MenuItem in cart");
                return ResponseEntity.ok(message);
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setMenuItemId(menuItemId);
        orderDetail.setOrderId(orderUser.getOrderId());
        orderDetail.setQuantity(1);
        orderDetailService.saveOrderDetail(orderDetail);
        List<OrderDetail> newUserorderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrderById(deliveryOrderId);
        deliveryOrder.setDeliveryOrderStatusId(1);
        deliveryOrderService.saveDeliveryOrder(deliveryOrder);
        return ResponseEntity.ok(newUserorderDetails);
    }

    // @Scheduled(fixedRate = 1000)
    public void getDeliveryOrderAndSend() {
        List<OrderDetail> listOrderDetail = orderDetailService.getAllOrderDetails();
        sendToWebSocket(listOrderDetail);
    }

    private void sendToWebSocket(List<OrderDetail> listOrderDetail) {
        WebSocketMessage message = new WebSocketMessage();

        // Chuyển đổi List thành mảng
        Object[] deliveryOrderArray = listOrderDetail.toArray();

        // Gán mảng vào đối tượng WebSocketMessage
        message.setObjects(deliveryOrderArray);

        // Sử dụng mảng chứa đối tượng khi gửi thông điệp
        messagingTemplate.convertAndSend("/public/cartDetails", message);
    }

}

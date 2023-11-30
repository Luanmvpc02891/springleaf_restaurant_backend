package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.springleaf_restaurant_backend.security.config.websocket.WebSocketMessage;

import com.springleaf_restaurant_backend.security.config.JwtService;
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

    @GetMapping("/public/deliveryOrder/{deliveryOrderId}")
    public DeliveryOrder getOneDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId){
        return deliveryOrderService.getDeliveryOrderById(deliveryOrderId);
    }

    @PostMapping("/public/create/deliveryOrder")
    public DeliveryOrder createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder){
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }

    @PutMapping("/public/update/deliveryOrder")
    public DeliveryOrder updateDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder){
        return deliveryOrderService.saveDeliveryOrder(deliveryOrder);
    }
    
    @DeleteMapping("/public/delete/deliveryOrder/{deliveryOrderId}")
    public void deleteDeliveryOrder(@PathVariable("deliveryOrderId") Long deliveryOrderId){
        deliveryOrderService.deleteDeliveryOrder(deliveryOrderId);
    }


    // Load thông tin giỏ hàng user
    @GetMapping("/public/user/getCartByUser")
    public ResponseEntity<DeliveryOrder> getUserCart(
        @RequestHeader("Authorization") String jwtToken
    ){
        String username = jwtService.extractUsername(jwtToken.substring(7));
        Optional<User> userByToken = userService.findByUsername(username);
        Optional<DeliveryOrderType> type = deliveryOrderTypeService.getDeliveryOrderTypeByName("Delivery Order");
        Optional<DeliveryOrder> cart = deliveryOrderService.getDeliveryOrdersByUserIdAndTypeAndActive(userByToken.get().getUserId(), type.get().getDeliveryOrderTypeId(), true);
        if(userByToken.isEmpty()){
            return ResponseEntity.ok(null);
        }
        else if(type.isEmpty()){
            return ResponseEntity.ok(null);
        }
        else if(cart.isPresent()){
            // Sử dụng cart đang có trạng thái true
            return ResponseEntity.ok(cart.get());
        }else{
            // Khi không có cart nào true thì sẽ tạo mới
            DeliveryOrder newCart = new DeliveryOrder();
            newCart.setCustomerId(userByToken.get().getUserId());
            newCart.setDeliveryOrderTypeId(type.get().getDeliveryOrderTypeId());
            newCart.setActive(true);
            deliveryOrderService.saveDeliveryOrder(newCart);
            return ResponseEntity.ok(newCart);
        }
    }

    @PostMapping("/public/create/user/getOrderByUser")
    public ResponseEntity<Order> getOrderByUser(
        @RequestHeader("Authorization") String jwtToken,
        @RequestBody Long deliveryOrderId
    ){
        Optional<Order> order = orderService.getOrdersByDeliveryOrderId(deliveryOrderId);
        if(order.isPresent()){
            return ResponseEntity.ok(order.get());
        }else{
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/public/create/user/getOrderDetailByUser")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByUser(
        @RequestHeader("Authorization") String jwtToken,
        @RequestBody Long orderId
    ){
        List<OrderDetail> orderDetail = orderDetailService.getOrderDetailsByOrderId(orderId);
        if(orderDetail != null){
            return ResponseEntity.ok(orderDetail);
        }else{
            return ResponseEntity.ok(null);
        }
    }
    
    @PostMapping("/public/create/addToCart/{menuItemId}/{deliveryOrderId}/{orderId}")
    public ResponseEntity<?> addToCart(
        @RequestHeader("Authorization") String jwtToken, // Người dùng
        @PathVariable("menuItemId") Long menuItemId, // Sản phẩm
        @PathVariable("deliveryOrderId") Long deliveryOrderId, // Giỏ hàng
        @PathVariable("orderId") Long orderId // Order
    ){
        MessageResponse message = new MessageResponse();
        // Kiểm tra người dùng theo jwt
        String username = jwtService.extractUsername(jwtToken.substring(7));
        Optional<User> userByToken = userService.findByUsername(username);
        if(userByToken.isEmpty()){
            message.setMessage("User not found"); 
            return ResponseEntity.ok(message);
        }
        Optional<DeliveryOrderType> type = deliveryOrderTypeService.getDeliveryOrderTypeByName("Delivery Order");
        if(type.isEmpty()){
            message.setMessage("Type not found"); 
            return ResponseEntity.ok(message);
        }
        Order orderUser = orderService.getOrderById(orderId);
        if(orderId == null){
            orderUser = new Order();
        }else{
            orderUser = orderService.getOrdersByDeliveryOrderId(deliveryOrderId).get();
        }
        List<OrderDetail> listOrderDetail = orderDetailService.getOrderDetailsByOrderId(orderId);
        for (OrderDetail orderDetail : listOrderDetail) {
            if(orderDetail.getMenuItemId() == menuItemId){
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


    //@Scheduled(fixedRate = 1000)
    public void getDeliveryOrderAndSend(){
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
        messagingTemplate.convertAndSend("/public/greetings", message);
    }

    
}

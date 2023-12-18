package com.springleaf_restaurant_backend.user.restcontrollers.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.entities.DeliveryOrder;
import com.springleaf_restaurant_backend.user.entities.Discount;
import com.springleaf_restaurant_backend.user.entities.MessageResponse;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.service.BillDetailService;
import com.springleaf_restaurant_backend.user.service.BillService;
import com.springleaf_restaurant_backend.user.service.DeliveryOrderService;
import com.springleaf_restaurant_backend.user.service.DiscountService;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;

@RestController
public class UserFeatureRestController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    DiscountService discountService;
    @Autowired
    DeliveryOrderService deliveryOrderService;

    @PostMapping("/public/checkout-cod/{orderId}/{totalAmount}/{discountCode}/{deliveryOrderId}")
    public ResponseEntity<MessageResponse> userCheckOut(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody List<OrderDetail> listItem,
            @PathVariable("orderId") Long orderId,
            @PathVariable("totalAmount") Double totalAmount,
            @PathVariable("totalAmount") Long deliveryOrderId,
            @PathVariable("discountCode") String discountCode) {
        String token = jwtToken.substring(7);
        String username = jwtService.extractUsername(token);

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Bill bill = new Bill();
            bill.setUserId(user.get().getUserId());
            bill.setBankNumber(null);
            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = formatter.format(currentDate);
            bill.setBillTime(formattedDate);
            bill.setOrderId(orderId);
            bill.setAddress(user.get().getAddress());
            bill.setPaymentMethod(Long.valueOf(1));
            bill.setTotalAmount(totalAmount);
            billService.saveBill(bill);

            for (OrderDetail orderDetail : listItem) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBill(bill.getBillId());
                billDetail.setMenuItemId(orderDetail.getMenuItemId());
                billDetail.setQuantity(orderDetail.getQuantity());
                billDetailService.saveBillDetail(billDetail);
                orderDetailService.deleteOrderDetail(orderDetail.getOrderDetailId());
            }

            if (!discountCode.equals("noDiscount")) {
                Discount discount = discountService.getDiscountByDiscountCode(discountCode);
                discount.setActive(false);
                discountService.saveDiscount(discount);
            }
            DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrderById(deliveryOrderId);
            deliveryOrder.setDeliveryOrderStatusId(7);
            deliveryOrderService.saveDeliveryOrder(deliveryOrder);

            MessageResponse mess = new MessageResponse("Checkout success");
            return ResponseEntity.ok(mess);
        } else {
            MessageResponse mess = new MessageResponse("Checkout failed");
            return ResponseEntity.ok(mess);
        }
    }

    @PostMapping("/public/config-user-cart")
    public ResponseEntity<MessageResponse> postMethodName(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody List<OrderDetail> listItem) {
        MessageResponse message = new MessageResponse();
        String jwt = jwtToken.substring(7);
        Optional<User> user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if (user.isPresent()) {
            DeliveryOrder delivery = deliveryOrderService.findByCustomerId(user.get().getUserId());
            delivery.setDeliveryOrderStatusId(2);
            delivery.setActive(false);
            //deliveryOrderService.saveDeliveryOrder(delivery);

            message.setMessage("Success");
            return ResponseEntity.ok(message);
        } else {
            message.setMessage("False");
            return ResponseEntity.ok(message);
        }
    }

}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.VNPayService;
import com.springleaf_restaurant_backend.user.entities.OrderData;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class VNpayRestController {
    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/public/create/submitOrder")
    public Map<String, String> submitOrder(@RequestBody OrderData orderData, HttpServletRequest request) {
        int orderTotal = orderData.getOrderTotal();
        String orderInfo = orderData.getOrderInfo();

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);

        return response;
    }

    @GetMapping("/public/vnpay-payment")
    @ResponseBody
    public Map<String, Object> getPaymentDetails(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("paymentStatus", paymentStatus == 1 ? "Order success" : "Order failed");
        paymentDetails.put("orderInfo", orderInfo);
        paymentDetails.put("paymentTime", paymentTime);
        paymentDetails.put("transactionId", transactionId);
        paymentDetails.put("totalPrice", totalPrice);
        System.out.println(paymentDetails);
        return paymentDetails;
    }

}

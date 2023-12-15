package com.springleaf_restaurant_backend.user.restcontrollers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.VNPayService;
import com.springleaf_restaurant_backend.user.entities.Bill;
import com.springleaf_restaurant_backend.user.entities.BillDetail;
import com.springleaf_restaurant_backend.user.entities.OrderData;
import com.springleaf_restaurant_backend.user.entities.OrderDetail;
import com.springleaf_restaurant_backend.user.service.BillDetailService;
import com.springleaf_restaurant_backend.user.service.BillService;
import com.springleaf_restaurant_backend.user.service.OrderDetailService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
public class VNpayRestController {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;

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
    public
    // Map<String, Object>
    void getPaymentDetails(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

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
        if (paymentStatus == 1) {
            if(orderInfo != null){
                orderMethod(orderInfo, Double.valueOf(totalPrice));
            }
            
            String queryString = "?paymentStatus="
                    + URLEncoder.encode((String) paymentDetails.get("paymentStatus"), StandardCharsets.UTF_8.toString())
                    +
                    "&orderInfo=" + URLEncoder.encode(orderInfo, StandardCharsets.UTF_8.toString()) +
                    "&paymentTime=" + URLEncoder.encode(paymentTime, StandardCharsets.UTF_8.toString()) +
                    "&transactionId=" + URLEncoder.encode(transactionId, StandardCharsets.UTF_8.toString()) +
                    "&totalPrice=" + URLEncoder.encode(totalPrice, StandardCharsets.UTF_8.toString());

            // Chuyển hướng với query parameter
            response.sendRedirect("http://localhost:4200/user/index/" + queryString);
        } else {

        }

        //System.out.println("here " + paymentDetails);
        // return paymentDetails;
    }

    public void orderMethod(String orderInfo, Double totalAmount) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + orderInfo);
        String[] orderInfoArray = orderInfo.split(",");
        if (orderInfoArray.length > 0) {
            // Lấy giá trị đầu tiên và gán vào biến
            Long orderId = Long.parseLong(orderInfoArray[0]);

            // Tạo danh sách để lưu các giá trị còn lại
            List<Long> orderDetailIds = Arrays.asList(orderInfoArray)
                    .stream()
                    .skip(1) // Bỏ qua phần tử đầu tiên
                    .map(Long::parseLong) // Chuyển đổi từ chuỗi sang số
                    .collect(Collectors.toList());

            // Bây giờ firstNumber chứa giá trị đầu tiên, và remainingNumbers chứa các giá
            // trị còn lại
            System.out.println("First Number: " + orderId);
            System.out.println("Remaining Numbers: " + orderDetailIds);
            Bill bill = new Bill();
            bill.setUserId(null);
            bill.setOrderId(orderId);
            bill.setPaymentMethod(Long.valueOf(2));
            bill.setTotalAmount(totalAmount);
            bill.setAddress(null);
            bill.setBankNumber(null);
            bill.setBillTime(null);
            billService.saveBill(bill);
            BillDetail billDetail = new BillDetail();
            for (Long long1 : orderDetailIds) {
                OrderDetail orderDetail = orderDetailService.getOrderDetailById(long1);
                billDetail.setBill(bill.getBillId());
                billDetail.setMenuItemId(orderDetail.getMenuItemId());
                billDetail.setQuantity(orderDetail.getQuantity());
                billDetailService.saveBillDetail(billDetail);
                orderDetailService.deleteOrderDetail(long1);
            }
        } else {
            // Xử lý trường hợp mảng rỗng (nếu cần)
        }

    }

}

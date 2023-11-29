package com.springleaf_restaurant_backend.security.config.websocket;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSockerRestController {

    // Trả về đối tượng
    // @MessageMapping("/hello")
    // @SendTo("/topic/greetings")
    // public Greeting greeting(HelloMessage message) throws Exception {
    // //Thread.sleep(1000); // simulated delay
    // System.out.println(message.getName());
    // return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) +
    // "!");
    // }

    // Trả về chuỗi
    // @MessageMapping("/hello")
    // @SendTo("/topic/greetings")
    // public String greeting(HelloMessage message) throws Exception {
    // //Thread.sleep(1000); // simulated delay
    // System.out.println(message.getName());
    // // return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) +
    // "!");
    // return "Hello, " + message.getName() + "!";
    // }

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpUserRegistry simpUserRegistry;

    public WebSockerRestController(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    // @MessageMapping("/topic/{userId}")
    // @SendTo("/topic/greetings/{userId}")
    // public String handleStompMessage(@DestinationVariable Integer userId,
    // WebSocketMessage message) {
    // // Xử lý tin nhắn STOMP ở đây
    // System.out.println("Received login topic: " + userId);
    // System.out.println("Received name topic: " + message.getName());

    // // Trả về tin nhắn trả lời
    // return "I am " + message.getName();
    // }

    @MessageMapping("/public")
    @SendTo("/public/greetings")
    public WebSocketMessage handleStompMessagePublic(WebSocketMessage message) {
        // Xử lý tin nhắn STOMP ở đây
        System.out.println("Received login: " + message.getName());
        System.out.println("Received name: " + message.getObjects());

        // Trả về tin nhắn trả lời
        return message;
    }

    @MessageMapping("/private/{userId}")
    @SendTo("/private/greetings/{userId}")
    public String handleStompMessage(@DestinationVariable String userId, String name) {
        // Lấy thời gian hiện tại theo múi giờ Việt Nam
        Date currentVietnamTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String formattedServerTime = dateFormat.format(currentVietnamTime);

        // System.out.println("Server Time: " + formattedServerTime);

        // Trả về tin nhắn trả lời kèm thời gian hiện tại theo định dạng
        return formattedServerTime;
    }

    @MessageMapping("/private/turnToWaitTime/{userId}")
    @SendTo("/private/turnToWaitTime/{userId}")
    public String turnToWaitTime(@DestinationVariable String userId, String clientTime) {
        System.out.println("User Id: " + userId);
        System.out.println("Client Time: " + clientTime);

        try {
            // Parse thời gian từ client
            SimpleDateFormat clientDateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
            Date clientDate = clientDateFormat.parse(clientTime);

            // Lấy thời gian hiện tại theo múi giờ Việt Nam
            Date currentVietnamTime = new Date();

            // Tính sự chênh lệch giữa thời gian client và thời gian hiện tại
            Duration duration = Duration.between(clientDate.toInstant(), currentVietnamTime.toInstant());

            // Trả về chuỗi đại diện cho thời gian chênh lệch
            return duration.toString();

        } catch (ParseException e) {
            // Xử lý nếu có lỗi xảy ra khi parse thời gian từ client
            e.printStackTrace();
            return "Error parsing client time";
        }
    }

}

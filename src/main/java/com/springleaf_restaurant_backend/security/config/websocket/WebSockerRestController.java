package com.springleaf_restaurant_backend.security.config.websocket;

import java.security.Principal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        System.out.println("Socket");
        System.out.println("User Id: " + userId);

        // Lấy thời gian hiện tại theo múi giờ Việt Nam
        Instant currentVietnamTime = Instant.now().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant();

        // Định dạng thời gian theo "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        String formattedTime = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .format(currentVietnamTime);

        System.out.println(formattedTime);

        // Trả về tin nhắn trả lời kèm thời gian hiện tại theo múi giờ Việt Nam
        return formattedTime;
    }

}
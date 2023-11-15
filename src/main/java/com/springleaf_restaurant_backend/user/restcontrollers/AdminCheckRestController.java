package com.springleaf_restaurant_backend.user.restcontrollers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCheckRestController {

    @GetMapping("/v1/auth/login-google")
    public void loginWithGoogle(){ }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> handleGoogleCallback() {
        // Xử lý mã code từ Google OAuth2
        // Thực hiện các hành động cần thiết, ví dụ: lấy thông tin người dùng
        System.out.println("Success riderect");
        // Trả về một ResponseEntity với body là thông điệp và status code là 200 OK
        return ResponseEntity.ok("Callback from Google received. Code: ");
    }
}

package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.user.entities.User;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/auth/your-profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        Optional<User> profile = userRepository.findByUsername(username);
        if (profile.isPresent()) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/auth/your-profile/update")
    public User updateProfile(@RequestHeader("Authorization") String authorizationHeader, 
        @RequestBody User updatedUserData) 
        {
            String token = authorizationHeader.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);
            // Tìm người dùng trong cơ sở dữ liệu
            Optional<User> userRequest = userRepository.findByUsername(username);

            if (userRequest.isPresent()) {
                User user = userRequest.get();
                user.setFullName(updatedUserData.getFullName());
                user.setEmail(updatedUserData.getEmail());
                user.setAddress(updatedUserData.getAddress());
                user.setPhone(updatedUserData.getPhone());
                return userRepository.save(user);
            } else {
                return null;
            }
        
    }
}

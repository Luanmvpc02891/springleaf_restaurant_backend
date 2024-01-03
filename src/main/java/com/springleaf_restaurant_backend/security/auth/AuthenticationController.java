package com.springleaf_restaurant_backend.security.auth;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.entities.token.Token;
import com.springleaf_restaurant_backend.security.entities.token.TokenType;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/access-code")
    public ResponseEntity<String> accessCode(@RequestParam String email, @RequestParam String typeCode) {
        System.out.println(typeCode);
        return ResponseEntity.ok(service.accessCode(email, typeCode));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/auto-login")
    public ResponseEntity<AuthenticationResponse> getUsername(
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {
        return ResponseEntity.ok(service.authenticateAutoByToken(authorizationHeader));
    }

    @PostMapping("/config-password")
    public ResponseEntity<String> configPassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody String password) {
        return ResponseEntity.ok(service.configPassword(authorizationHeader, password));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody String password) {
        return ResponseEntity.ok(service.changePassword(authorizationHeader, password));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody String password) {
        return ResponseEntity.ok(service.forgotPassword(authorizationHeader, password));
    }

    @GetMapping("/your-profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        System.out.println(token);
        String username = jwtService.extractUsername(token);
        Optional<User> profile = userService.findByUsername(username);
        if (profile.isPresent()) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/your-profile/update")
    public ResponseEntity<AuthenticationResponse> updateProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User updatedUserData) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        // Tìm người dùng trong cơ sở dữ liệu
        Optional<User> userRequest = userService.findByUsername(username);
        User userByEmail = userService.findByEmail(updatedUserData.getEmail());
        User userByPhone = userService.findByPhone(updatedUserData.getPhone());
        AuthenticationResponse response = new AuthenticationResponse();
        if (userByEmail != null) {
            if (!userByEmail.equals(userRequest.get())) {
                response = AuthenticationResponse.builder()
                        .error("Email cannot update")
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        if (userByPhone != null) {
            if (!userByPhone.equals(userRequest.get())) {
                response = AuthenticationResponse.builder()
                        .error("Phone cannot update")
                        .build();
                return ResponseEntity.ok(response);
            }

        }
        if (userRequest.isPresent()) {
            User user = userRequest.get();
            user.setFullName(updatedUserData.getFullName());
            user.setEmail(updatedUserData.getEmail());
            user.setAddress(updatedUserData.getAddress());
            user.setPhone(updatedUserData.getPhone());
            user.setRestaurantBranchId(updatedUserData.getRestaurantBranchId());
            user.setImage(updatedUserData.getImage());
            userService.createUser(user);
            List<String> role_name = userService.findRoleNamesByUserId(user.getUserId());
            if (role_name != null) {
                user.setRoleName(role_name);
            } else {
                System.out.println("Không có role nào cho người dùng này.");
            }
            response = AuthenticationResponse.builder()
                    .accessToken(token)
                    .user(user)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            response = AuthenticationResponse.builder()
                    .error("Cannot update")
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/choose-restaurant/update")
    public User updateRestaurant(@RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User updatedUserData) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        // Tìm người dùng trong cơ sở dữ liệu
        Optional<User> userRequest = userService.findByUsername(username);

        if (userRequest.isPresent()) {
            User user = userRequest.get();
            user.setRestaurantBranchId(updatedUserData.getRestaurantBranchId());
            return userService.createUser(user);
        } else {
            return null;
        }
    }

    @PostMapping("/login-with-google/{email}")
    public ResponseEntity<AuthenticationResponse> googleLogin(
            @PathVariable("email") String email) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + email);
        AuthenticationResponse response = new AuthenticationResponse();
        User user = userService.findByEmail(email);
        if (user != null) {
            if (!user.isStatus()) {
                response = AuthenticationResponse.builder()
                        .error("Account is disabled")
                        .build();
            }
            List<String> role_name = userService.findRoleNamesByUserId(user.getUserId());
            if (role_name != null) {
                user.setRoleName(role_name);
            } else {
                System.out.println("Không có role nào cho người dùng này.");
            }
            revokeAllUserTokens(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(user, jwtToken);
            response = AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .user(user)
                    .build();
        } else {
            response = AuthenticationResponse.builder()
                    .error("User not found")
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getUserId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }
}
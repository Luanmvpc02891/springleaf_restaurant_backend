package com.springleaf_restaurant_backend.security.auth;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  
  private final AuthenticationService service;
  private final JwtService jwtService;
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) throws Exception {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/access-code")
  public ResponseEntity<String> accessCode(@RequestParam String email){
    System.out.println("API calling...");
    return ResponseEntity.ok(service.accessCode(email));
  }
  
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    
    return ResponseEntity.ok(service.authenticate(request)); 
  }
  
  // @PostMapping("/refresh-token")
  // public void refreshToken(
  //     HttpServletRequest request,
  //     HttpServletResponse response
  // ) throws IOException {
  //   service.refreshToken(request, response);
  // }

    @PostMapping("/auto-login")
    public ResponseEntity<AuthenticationResponse> getUsername(@RequestHeader("Authorization") String authorizationHeader) 
    throws Exception {
        return ResponseEntity.ok(service.authenticateAutoByToken(authorizationHeader));
    }

    @PostMapping("/config-password")
    public ResponseEntity<String> configPassword(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody String password
    ){
        return ResponseEntity.ok(service.configPassword(authorizationHeader, password));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody String password
    ){
        return ResponseEntity.ok(service.changePassword(authorizationHeader, password));
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
    public User updateProfile(@RequestHeader("Authorization") String authorizationHeader, 
        @RequestBody User updatedUserData) 
        {
            String token = authorizationHeader.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);
            // Tìm người dùng trong cơ sở dữ liệu
            Optional<User> userRequest = userService.findByUsername(username);

            if (userRequest.isPresent()) {
                User user = userRequest.get();
                user.setFullName(updatedUserData.getFullName());
                user.setEmail(updatedUserData.getEmail());
                user.setAddress(updatedUserData.getAddress());
                user.setPhone(updatedUserData.getPhone());
                return userService.createUser(user);
            } else {
                return null;
            }
    }

    // @PostMapping("/logout")
    // public void logout(){
    //   logoutService.logout(null, null, null);
    // }

}
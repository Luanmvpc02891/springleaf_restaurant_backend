package com.springleaf_restaurant_backend.security.auth;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.config.LogoutService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  
  private final AuthenticationService service;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final LogoutService logoutService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) throws Exception {
    return ResponseEntity.ok(service.register(request));
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

    @GetMapping("/your-profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        System.out.println(token);
        String username = jwtService.extractUsername(token);
        Optional<User> profile = userRepository.findByUsername(username);
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
    
    // @PostMapping("/logout")
    // public void logout(){
    //   logoutService.logout(null, null, null);
    // }

}

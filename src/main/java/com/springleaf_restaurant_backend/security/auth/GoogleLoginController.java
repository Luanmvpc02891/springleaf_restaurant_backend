package com.springleaf_restaurant_backend.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.entities.UserRole;
import com.springleaf_restaurant_backend.security.entities.token.Token;
import com.springleaf_restaurant_backend.security.entities.token.TokenType;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.service.UserRoleService;
import com.springleaf_restaurant_backend.security.service.UserService;

@RestController
public class GoogleLoginController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    TokenRepository tokenRepository;
    ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/auth/login")
    public void loginWithGoogle() {
        // Tạo URL callback cho endpoint "/login/oauth2/code/google"
        // System.out.println("Vô đây rồi");
        // NetHttpTransport transport = new NetHttpTransport();
        // JacksonFactory factory = JacksonFactory.getDefaultInstance();
        // GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport,
        // factory)
        // .setAudience(Collections.singletonList("709298806232-0gqc6kn27s7653irqbdv8l0iv4ro9rr5.apps.googleusercontent.com"))
        // .build();

    }

    @GetMapping("/login/oauth2/code/google")
    public void handleGoogleCallback(Authentication authentication) throws Exception {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            // Lấy thông tin người dùng từ Principal
            OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
            String email = (String) oAuth2User.getAttribute("email"); // Email google
            String givenName = (String) oAuth2User.getAttribute("given_name"); // Tên
            String pictureUrl = (String) oAuth2User.getAttribute("picture"); // Hình trên google
            String sub = (String) oAuth2User.getAttribute("sub"); // ID định danh trên google
            
            User userData = userService.findByEmail(email);
            if (userData != null) {
                // Đăng nhập // Đã có tài khoản
                System.out.println("Đăng nhập gg thành công");
            } else {
                // Đăng ký
                // Tạo ra user mới
                userData = new User();
                userData.setFullName(givenName);
                userData.setUsername(email);
                userData.setPassword(passwordEncoder.encode(sub));
                userData.setPhone(null);
                userData.setEmail(email);
                userData.setImage(pictureUrl);
                userData.setStatus(true);
                userService.createUser(userData);
                // Thiết lập token
                var jwtToken = jwtService.generateToken(userData);

                var token = Token.builder()
                        .user(userData)
                        .token(jwtToken)
                        .tokenType(TokenType.BEARER)
                        .expired(false)
                        .revoked(false)
                        .build();
                tokenRepository.save(token);
                System.out.println("USER ROLE:" + userData.getUserId());
                // Thiết lập User Role
                UserRole ur = new UserRole();
                ur.setRoleId(3); // Mặc định tạo tài khoản USER
                ur.setUserId(userData.getUserId());
                
                userRoleService.createUserRole(ur);
                System.out.println("Đăng ký tài khoản mới");
            }
        }
    }

}
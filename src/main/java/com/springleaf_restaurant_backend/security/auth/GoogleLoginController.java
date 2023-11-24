package com.springleaf_restaurant_backend.security.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
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

    @GetMapping("/login")
    public void loginWithGoogle() {
        // Tạo URL callback cho endpoint "/login/oauth2/code/google"
        System.out.println("Vô đây rồi");
        NetHttpTransport transport = new NetHttpTransport();
            JacksonFactory factory = JacksonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                    .setAudience(Collections.singletonList("709298806232-0gqc6kn27s7653irqbdv8l0iv4ro9rr5.apps.googleusercontent.com"))
                    .build();
        
    }

    @GetMapping("/login/oauth2/code/google")
    public RedirectView  handleGoogleCallback(Authentication authentication) throws Exception {
        if (authentication instanceof OAuth2AuthenticationToken) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        // Lấy thông tin người dùng từ Principal
        OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
        System.out.println(oAuth2User);
        String email = (String) oAuth2User.getAttribute("email");
        String givenName = (String) oAuth2User.getAttribute("given_name");
        String pictureUrl = (String) oAuth2User.getAttribute("picture");
        System.out.println("email: " + email + " name: " + givenName + " picture: " + pictureUrl);
        User userData =  userService.findByEmail(email);
        if(userData != null){
            System.out.println("Đăng nhập gg thành công");
        }else{
             userData = new User();
             userData.setFullName(givenName);
             userData.setUsername(email);
             userData.setPassword(passwordEncoder.encode(email));
             userData.setPhone(null);
             userData.setEmail(email);
             userService.createUser(userData);
             var jwtToken = jwtService.generateToken(userData);
            
            var token = Token.builder()
                .user(userData)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
            tokenRepository.save(token);
            UserRole ur = new UserRole();
            ur.setRoleId(3);
            ur.setUserId(userData.getUserId());
            userRoleService.createUserRole(ur);
             System.out.println("Đăng ký tài khoản mới");
        }
         }
        
        return new RedirectView ( "http://localhost:4200/user/index", true);
    }
}
package com.springleaf_restaurant_backend.user.restcontrollers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@RestController
public class AdminCheckRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/v1/auth/login-google")
    public void loginWithGoogle(){ }

    @GetMapping("/login/oauth2/code/google")
    public RedirectView  handleGoogleCallback(Authentication authentication) {
        System.out.println("Success riderect");
        if (authentication instanceof OAuth2AuthenticationToken) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        // Lấy thông tin người dùng từ Principal
        OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
        String email = (String) oAuth2User.getAttribute("email");
        String givenName = (String) oAuth2User.getAttribute("given_name");
        String pictureUrl = (String) oAuth2User.getAttribute("picture");
        System.out.println("email: " + email + " name: " + givenName + " picture: " + pictureUrl);
        // User userData =  userRepository.findByEmail(email);
        // if(userData != null){

         }else{
        //     userData = new User();
        //     userData.setEmail(email);
        // }
         }
        //return ResponseEntity.ok("Callback from Google received. Code: ");
        return new RedirectView ( "http://localhost:4200/user/index", true);
    }
}

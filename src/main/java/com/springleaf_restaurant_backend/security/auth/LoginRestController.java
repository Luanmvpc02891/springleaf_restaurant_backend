package com.springleaf_restaurant_backend.security.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/google-login")
public class LoginRestController {
    @GetMapping
    public Map<String,Object> getMethodName(OAuth2AuthenticationToken auth2AuthenticationToken) {
        Object email = auth2AuthenticationToken.getPrincipal().getAttributes().get("email");
        System.out.println(email);
        return auth2AuthenticationToken.getPrincipal().getAttributes();
    }
    
}

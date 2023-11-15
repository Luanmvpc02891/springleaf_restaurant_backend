package com.springleaf_restaurant_backend.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.springleaf_restaurant_backend.security.repositories.RoleRepository;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;
  @Autowired
  RoleRepository roleRepository;

  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
          .requestMatchers(
            "/public/**",
            "https://accounts.google.com/**",
            "https://drive.google.com/**",
            "https://springleafrestaurant.onrender.com/**",
            "/auth/**",
            "/api/**",
            "https://accounts.google.com/**",
            "http://localhost:8082/public/api/Callback",
            "/login/oauth2/code/google"
          )
            .permitAll()

        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
        .anyRequest()
          .authenticated()
          
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(oauth2Login ->
            oauth2Login
                .successHandler((request, response, authentication) -> {
                    callYourApiAfterLogin(authentication, response);
                })
            )
        
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        
    ;
    return http.build();
  }


  public void callYourApiAfterLogin(Authentication authentication, HttpServletResponse response) throws IOException {
    if (authentication instanceof OAuth2AuthenticationToken) {
    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

    // Lấy thông tin người dùng từ Principal
    OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
    String userId = oAuth2User.getName();
    String email = (String) oAuth2User.getAttribute("email");
    String givenName = (String) oAuth2User.getAttribute("given_name");
    String pictureUrl = (String) oAuth2User.getAttribute("picture");
    System.out.println("email: " + email + " name: " + givenName + " picture: " + pictureUrl);
}
}

}

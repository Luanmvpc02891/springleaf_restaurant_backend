package com.springleaf_restaurant_backend.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.springleaf_restaurant_backend.security.auth.GoogleLoginController;
import com.springleaf_restaurant_backend.security.repositories.RoleRepository;

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
  private final GoogleLoginController googleLoginController;

  
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
                  try {
                    googleLoginController.handleGoogleCallback(authentication);
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                  OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
                  String email = (String) oAuth2User.getAttribute("email");
                  String redirectUrl = "http://localhost:4200/user/index?" + email;
                  response.sendRedirect(redirectUrl);
            })
        )
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        
    ;
    return http.build();
  }
}

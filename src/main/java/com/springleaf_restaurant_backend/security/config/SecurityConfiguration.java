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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.UriComponentsBuilder;

import com.springleaf_restaurant_backend.security.auth.GoogleLoginController;
import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final GoogleLoginController googleLoginController;
  private final LogoutService logoutService;
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
            //"https://accounts.google.com/**",
            "/login/oauth2/code/google",
            "/auth2/**")
        .permitAll()
        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(oauth2Login -> oauth2Login
            .successHandler((request, response, authentication) -> {
              try {
                googleLoginController.handleGoogleCallback(authentication);
              } catch (Exception e) {
                e.printStackTrace();
              }
              OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
              OAuth2User oAuth2User = (OAuth2User) oauthToken.getPrincipal();
              String email = (String) oAuth2User.getAttribute("email");
              System.out.println("Dữ liệu: " + oAuth2User);
              // Thêm dữ liệu vào URL
              String redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:4200/user/index")
                  .queryParam("email", email)
                  .queryParam("additionalData", "yourAdditionalData")
                  .build().toUriString();

              // Redirect với URL mới
              response.sendRedirect(redirectUrl);
            }))
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/auth2/logout", "POST"))
        .addLogoutHandler(
            (request, response, authentication) -> logoutService.logout(request, response, authentication))
        // .addLogoutHandler((request, response, authentication) ->
        // logoutService.logout(request, response, authentication))
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())

    ;
    return http.build();
  }

}

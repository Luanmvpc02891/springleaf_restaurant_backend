// package com.springleaf_restaurant_backend.security.config;

// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.authentication.logout.LogoutHandler;

// import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
// import com.springleaf_restaurant_backend.user.entities.Role;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// @EnableMethodSecurity
// public class SecurityConfiguration {

//   private final JwtAuthenticationFilter jwtAuthFilter;
//   private final AuthenticationProvider authenticationProvider;
//   private final LogoutHandler logoutHandler;
//   @Autowired
//   RoleRepository roleRepository;

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     List<Role> allRoles = roleRepository.findAll();
//     List<String> roleNames = allRoles.stream()
//             .map(Role::getRoleSa)
//             .collect(Collectors.toList());
//     http
//         .csrf()
//         .disable()
        
//         .authorizeHttpRequests()
//         .requestMatchers(
//           "/api/**",
//           "https://accounts.google.com/**",
//           "https://drive.google.com/**"
//         )
//         .permitAll()


//         //.requestMatchers("/api/products").hasAnyRole("ADMIN", "MANAGER")
        
//         .anyRequest()
//           .authenticated()
//         .and()
//           .sessionManagement()
//           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()
//         .authenticationProvider(authenticationProvider)
//         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//         .logout()
//         .logoutUrl("/api/v1/auth/logout")
//         .addLogoutHandler(logoutHandler)
//         .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//     ;

//     return http.build();
//   }

  
// }

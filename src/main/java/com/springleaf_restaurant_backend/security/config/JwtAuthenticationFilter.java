package com.springleaf_restaurant_backend.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.user.entities.User;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    // Bỏ qua url nếu là đường dẫn đăng nhập
    if (request.getServletPath().contains("/auth")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userName;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    userName = jwtService.extractUsername(jwt);
    // Nếu những url public sẽ được bỏ qua phần secu
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      Optional<User> user = userRepository.findByUsername(userName);
      List<GrantedAuthority> authoritiesList = new ArrayList<>();
      if(user != null){
          authoritiesList.add(new SimpleGrantedAuthority(roleRepository.findRoleSaByRoleId(user.get().getRoleId()))); 
      }
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
      
      var isTokenValid = tokenRepository.findByToken(jwt)
          .map(t -> !t.isExpired() && !t.isRevoked())
          .orElse(false);
      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            //userDetails.getAuthorities()
            authoritiesList
        );
        System.out.println("Test" + authoritiesList);
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}

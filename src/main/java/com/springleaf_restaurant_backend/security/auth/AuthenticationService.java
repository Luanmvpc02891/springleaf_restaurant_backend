// package com.springleaf_restaurant_backend.security.auth;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.springleaf_restaurant_backend.security.config.JwtService;
// import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
// import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
// import com.springleaf_restaurant_backend.security.repositories.UserRepository;
// import com.springleaf_restaurant_backend.security.token.Token;
// import com.springleaf_restaurant_backend.security.token.TokenType;
// import com.springleaf_restaurant_backend.user.entities.User;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpHeaders;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
// import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Service;


// import java.io.IOException;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class AuthenticationService {
//   private final UserRepository userRepository;
//   private final TokenRepository tokenRepository;
//   private final PasswordEncoder passwordEncoder;
//   private final JwtService jwtService;
//   private final AuthenticationManager authenticationManager;

//   @Autowired
//   RoleRepository roleRepository;

//   public AuthenticationResponse register(RegisterRequest request) throws Exception {
//     User existingUserByEmail = userRepository.findByEmail(request.getEmail());
//     Optional<User> existingUserByUsername = userRepository.findByUsername(request.getUsername());

//     if (existingUserByEmail != null) {
//       return AuthenticationResponse.builder()
//           .error("User with this email already exists")
//           .build();
//   } else if (existingUserByUsername.isPresent()) {
//       return AuthenticationResponse.builder()
//             .error("User with this username already exists")
//             .build();
//   } else {
//         var user = User.builder()
//             .username(request.getUsername())
//             .password(passwordEncoder.encode(request.getPassword()))
//             .roleId(2)
//             .fullName(request.getFullName())
//             .email(request.getEmail())
//             .build();
//         var savedUser = userRepository.save(user);
//         var jwtToken = jwtService.generateToken(user);
//         var refreshToken = jwtService.generateRefreshToken(user);
//         saveUserToken(savedUser, jwtToken);
//         return AuthenticationResponse.builder()
//             .accessToken(jwtToken)
//             .refreshToken(refreshToken)
//             .build();
//     }
//   }



//   public AuthenticationResponse authenticate(AuthenticationRequest request) {
//     authenticationManager.authenticate(
//         new UsernamePasswordAuthenticationToken(
//             request.getUserName(),
//             request.getPassword()
//         )
//     );
//     var user = userRepository.findByUsername(request.getUserName())
//         .orElseThrow();
//     var jwtToken = jwtService.generateToken(user);
//     var refreshToken = jwtService.generateRefreshToken(user);
//     if(user != null){
//       Integer roleId = user.getRoleId();
//       String roleName = roleRepository.findRoleSaByRoleId(roleId);
//       user.setRoleName(roleName);
//     }
//     revokeAllUserTokens(user);
//     saveUserToken(user, jwtToken);
//     return AuthenticationResponse.builder()
//         .accessToken(jwtToken)
//         .refreshToken(refreshToken)
//         .user(user)
//       .build();
//   }

//   private void saveUserToken(User user, String jwtToken) {
//     var token = Token.builder()
//         .user(user)
//         .token(jwtToken)
//         .tokenType(TokenType.BEARER)
//         .expired(false)
//         .revoked(false)
//         .build();
//     tokenRepository.save(token);
//   }

//   private void revokeAllUserTokens(User user) {
//     var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getUserId());
    
//     if (validUserTokens.isEmpty())
//         return;
    
//     validUserTokens.forEach(token -> {
//         token.setExpired(true);
//         token.setRevoked(true);
//     });
    
//     tokenRepository.saveAll(validUserTokens);
// }


//   public void refreshToken(
//           HttpServletRequest request,
//           HttpServletResponse response
//   ) throws IOException {
//     final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//     final String refreshToken;
//     final String userName;
//     if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//       return;
//     }
//     refreshToken = authHeader.substring(7);
//     userName = jwtService.extractUsername(refreshToken);
//     if (userName != null) {
//       var user = this.userRepository.findByUsername(userName)
//               .orElseThrow();
//       if (jwtService.isTokenValid(refreshToken, user)) {
//         var accessToken = jwtService.generateToken(user);
//         revokeAllUserTokens(user);
//         saveUserToken(user, accessToken);
//         var authResponse = AuthenticationResponse.builder()
//                 .accessToken(accessToken)
//                 .refreshToken(refreshToken)
//                 .build();
//         new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//       }
//     }
//   }
// }

package com.springleaf_restaurant_backend.security.auth;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.entities.UserRole;
import com.springleaf_restaurant_backend.security.entities.token.Token;
import com.springleaf_restaurant_backend.security.entities.token.TokenType;
import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.security.service.UserRoleService;
import com.springleaf_restaurant_backend.security.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserRoleService userRoleService;
  private final UserService userService;

  @Autowired
  RoleRepository roleRepository;

  public AuthenticationResponse register(RegisterRequest request) throws Exception {
    User existingUserByEmail = userRepository.findByEmail(request.getEmail());
    Optional<User> existingUserByUsername = userRepository.findByUsername(request.getUsername());

    if (existingUserByEmail != null) {
      return AuthenticationResponse.builder()
          .error("User with this email already exists")
          .build();
  } else if (existingUserByUsername.isPresent()) {
      return AuthenticationResponse.builder()
            .error("User with this username already exists")
            .build();
  } else {
        var user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .fullName(request.getFullName())
            .email(request.getEmail())
            .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        //var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        UserRole ur = new UserRole();
        ur.setRoleId(3);
        ur.setUserId(user.getUserId());
        userRoleService.createUserRole(ur);
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            //.refreshToken(refreshToken)
            .build();
    }
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserName(),
            request.getPassword()
        )
    );
    var user = userRepository.findByUsername(request.getUserName())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    //var refreshToken = jwtService.generateRefreshToken(user);
    if(user != null){
      List<String> role_name = userRepository.findRoleNamesByUserId(user.getUserId());
      if (role_name != null) {
        user.setRoleName(role_name);
      } else {
          System.out.println("Không có role nào cho người dùng này.");
      }
    }
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        //.refreshToken(refreshToken)
        .user(user)
      .build();
  }
  // ---- Login google -- //
  public AuthenticationResponse authenticateGoogleLogin(User user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        )
    );
    var jwtToken = jwtService.generateToken(user);
    //var refreshToken = jwtService.generateRefreshToken(user);
    if(user != null){
      List<String> role_name = userRepository.findRoleNamesByUserId(user.getUserId());
      if (role_name != null) {
        user.setRoleName(role_name);
      } else {
          System.out.println("Không có role nào cho người dùng này.");
      }
    }
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .user(user)
      .build();
  }

  public AuthenticationResponse authenticateAutoByToken(String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        String accessToken = authorizationHeader.substring(7);
        try {
            if (jwtService.isTokenExpired(accessToken)) {
                return AuthenticationResponse.builder()
                    .error("Session has expired")
                    .build();
            } else {
                var user = userRepository.findByUsername(this.jwtService.extractUsername(accessToken))
                    .orElseThrow();
                if(user != null){
                  List<String> role_name = userRepository.findRoleNamesByUserId(user.getUserId());
                  if (role_name != null) {
                    user.setRoleName(role_name);
                  } else {
                      System.out.println("Không có role nào cho người dùng này.");
                  }
                }
                return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    //.refreshToken(accessToken)
                    .user(user)
                    .build();
            }
        } catch (ExpiredJwtException e) {
            return AuthenticationResponse.builder()
                .error("Session has expired")
                .build();
        }
    } else {
        return AuthenticationResponse.builder()
            .error("Token was wrong")
            .build();
    }
}


  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getUserId());
    
    if (validUserTokens.isEmpty())
        return;
    
    validUserTokens.forEach(token -> {
        token.setExpired(true);
        token.setRevoked(true);
    });
    
    tokenRepository.saveAll(validUserTokens);
}


  // public void refreshToken(
  //         HttpServletRequest request,
  //         HttpServletResponse response
  // ) throws IOException {
  //   final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
  //   final String refreshToken;
  //   final String userName;
  //   if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
  //     return;
  //   }
  //   refreshToken = authHeader.substring(7);
  //   userName = jwtService.extractUsername(refreshToken);
  //   if (userName != null) {
  //     var user = this.userRepository.findByUsername(userName)
  //             .orElseThrow();
  //     if (jwtService.isTokenValid(refreshToken, user)) {
  //       var accessToken = jwtService.generateToken(user);
  //       revokeAllUserTokens(user);
  //       saveUserToken(user, accessToken);
  //       var authResponse = AuthenticationResponse.builder()
  //               .accessToken(accessToken)
  //               .refreshToken(refreshToken)
  //               .build();
  //       new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
  //     }
  //   }
  // }
}

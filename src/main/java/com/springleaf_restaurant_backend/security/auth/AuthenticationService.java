package com.springleaf_restaurant_backend.security.auth;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.Role;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.entities.UserRole;
import com.springleaf_restaurant_backend.security.entities.token.Token;
import com.springleaf_restaurant_backend.security.entities.token.TokenType;
import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.security.service.UserRoleService;
import com.springleaf_restaurant_backend.user.entities.MailInfo;
import com.springleaf_restaurant_backend.user.service.mail.MailerService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserRoleService userRoleService;
  private final MailerService mailerService;

  @Autowired
  RoleRepository roleRepository;

  public AuthenticationResponse register(RegisterRequest request) throws Exception {
    User existingUserByEmail = userRepository.findByEmail(request.getEmail());
    Optional<User> existingUserByUsername = userRepository.findByUsername(request.getUsername());
    Role role = roleRepository.findByRoleName("USER");
    if(jwtService.isTokenRegisterValid(request.getJwtToken(), request.getUsername())){
      return AuthenticationResponse.builder()
          .error("JWT is valid")
          .build();
    }
    if (existingUserByEmail != null) {
      return AuthenticationResponse.builder()
          .error("User with this email already exists")
          .build();
    } else if (existingUserByUsername.isPresent()) {
      return AuthenticationResponse.builder()
            .error("User with this username already exists")
            .build();
    } else if(role == null){
      return AuthenticationResponse.builder()
            .error("Role not found")
            .build();
    }else{
          var user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .fullName(request.getFullName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .status(true)
            .build();
          var savedUser = userRepository.save(user);
          var jwtToken = jwtService.generateToken(user);
          saveUserToken(savedUser, jwtToken);
          UserRole ur = new UserRole();
          ur.setRoleId(role.getRoleId());
          ur.setUserId(user.getUserId());
          userRoleService.createUserRole(ur);
          return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .build();
    }
  }

  public String accessCode(String email, String typeCode){
    User existingUserByEmail = userRepository.findByEmail(email);
    if (existingUserByEmail != null && typeCode.equals("register")) {
      return "User with this email already exists";
    }else{
      List<Integer> keys = generateRandomAccessCode();
      String jwtToken = jwtService.generateAccessRegisterToken(email);
      saveAccessRigisterToken(jwtToken);
      for (Integer key : keys) {
        jwtToken += String.valueOf(key);
      }
      try {
        MailInfo mail = new MailInfo();
        mail.setTo(email);
        mail.setKeys(keys);
        mail.setSubject(new String("Mã xác nhận đăng ký".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
       mailerService.send(mail);
      } catch (Exception e) {
        return null;
      }
      return jwtToken;
    }
  }

  public String configPassword(String jwt, String password){
    String jwtToken = jwt.substring(7);
    Optional<User> user = userRepository.findByUsername(jwtService.extractUsername(jwtToken));
    if(user.isEmpty()){
      return "User not found";
    }else{
      String pass = passwordEncoder.encode(password);
      System.out.println(user.get().getPassword());
      System.out.println(pass);
      System.out.println(passwordEncoder.matches(password,user.get().getPassword()));
      if(passwordEncoder.matches(password,user.get().getPassword())){
        
        return "Config password success";
      }else{
        return "Config password faile";
      }
    }
  }

  public String changePassword(String jwt, String password){
    String jwtToken = jwt.substring(7);
    Optional<User> user = userRepository.findByUsername(jwtService.extractUsername(jwtToken));
    if(user.isEmpty()){
      return "User not found";
    }else{
      User u = user.get();
      u.setPassword(passwordEncoder.encode(password));
      userRepository.save(u);
      return "Change password success";
    }
  }

  public String forgotPassword(String jwt, String password){
    String jwt2 = jwt.substring(7);
    String email = jwtService.extractUsername(jwt2);
    System.out.println(email);
    User user = userRepository.findByEmail(email);
    if(user == null){
      return "User not found";
    }else{
      User u = user;
      u.setPassword(passwordEncoder.encode(password));
      userRepository.save(u);
      return "Change password success";
    }
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    String userName = request.getUserName();
    String pass = request.getPassword();
    System.out.println(userName + pass);
    try {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserName(),
            request.getPassword()
        )
    );
    
    var user = userRepository.findByUsername(request.getUserName())
        .orElseThrow();
    if(user != null){
      if(!user.isStatus()){
        return AuthenticationResponse.builder()
          .error("Account is disabled")
        .build();
      }
      List<String> role_name = userRepository.findRoleNamesByUserId(user.getUserId());
      if (role_name != null) {
        user.setRoleName(role_name);
      } else {
          System.out.println("Không có role nào cho người dùng này.");
      }
      revokeAllUserTokens(user);
      var jwtToken = jwtService.generateToken(user);
      saveUserToken(user, jwtToken);
      return AuthenticationResponse.builder()
          .accessToken(jwtToken)
          .user(user)
        .build();
    }else{
      return AuthenticationResponse.builder()
          .error("User not found")
        .build();
    }
  } catch (Exception e) {
      return AuthenticationResponse.builder()
          .error("Invalid username or password")
        .build();
    }
    
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
        var user = userRepository.findByUsername(this.jwtService.extractUsername(accessToken))
                    .orElseThrow();
        var userDetails = User.builder()
            .username(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword()))
            .fullName(user.getFullName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .status(true)
            .build();
        try {
            if (!jwtService.isTokenValid(accessToken, userDetails)) {
                return AuthenticationResponse.builder()
                    .error("Session has expired")
                    .build();
            } else {
                
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

  private void saveAccessRigisterToken(String jwtToken) {
    var token = Token.builder()
        .user(null)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  public List<Integer> generateRandomAccessCode() {
    List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10); 
            randomNumbers.add(randomNumber);
        }
        return randomNumbers;
  }

}

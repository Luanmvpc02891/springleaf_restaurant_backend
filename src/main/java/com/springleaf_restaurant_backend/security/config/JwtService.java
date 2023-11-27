package com.springleaf_restaurant_backend.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.TokenRepository;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;

@Service
public class JwtService {
  @Autowired
  TokenRepository tokenRepository;
  @Autowired
  UserRepository userRepository;

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;
  @Value("${application.security.jwt.accessRegisterExpiration}")
  private long accessRegisterExpiration;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims); 
  }
  // Tạo access token
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails,
          long expiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenRevoked(String token){
    Optional<User> user = userRepository.findByUsername(extractUsername(token));
    if(!user.isEmpty()){
      if(!tokenRepository.findAllValidTokensByUserId(user.get().getUserId()).isEmpty()){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && isTokenRevoked(token);
  }

  public boolean isTokenExpired(String token) {
    return (extractExpiration(token).before(new Date()));
    
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // ------ Token cho Access Key ------- //
  public String generateAccessRegisterToken(String email) {
    return generateAccessRegisterToken(new HashMap<>(), email);
  }

  public String generateAccessRegisterToken(
      Map<String, Object> extraClaims,
      String email
  ) {
    return buildAccessRegisterToken(new HashMap<>(), email, accessRegisterExpiration);
  }

  private String buildAccessRegisterToken(
          Map<String, Object> extraClaims,
          String email,
          long accessRegisterExpiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(email)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + accessRegisterExpiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenRegisterValid(String token, String email) {
    try {
        String emailToken = extractUsername(token);
        return emailToken.equals(email) && !isTokenExpired(token);
    } catch (ExpiredJwtException ex) {
        // Xử lý khi token hết hạn
        return false;
    }
}

  
}
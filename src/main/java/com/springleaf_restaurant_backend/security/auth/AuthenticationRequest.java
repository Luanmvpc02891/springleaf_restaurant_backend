package com.springleaf_restaurant_backend.security.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
  private String userName;
  String password;
}

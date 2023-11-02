package com.springleaf_restaurant_backend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String fullName;
  private String username;
  private String password;
  private String phone;
  private String email;
  
}

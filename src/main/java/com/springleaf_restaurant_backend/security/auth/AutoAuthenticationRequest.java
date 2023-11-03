package com.springleaf_restaurant_backend.security.auth;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoAuthenticationRequest {
    String accessToken;
    String refreshToken;
}

package com.springleaf_restaurant_backend.security.config.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {

  private String name;
  private Object[] objects;

}

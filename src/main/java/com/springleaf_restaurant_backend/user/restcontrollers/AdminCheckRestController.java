package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.repositories.MenuItemRepository;

@RestController
@RequestMapping("/admin")
public class AdminCheckRestController {
    @Autowired
    MenuItemRepository menuItemRepository;

    @GetMapping("/checks")
    public ResponseEntity<String> getCategories() {
        String reponse = "Phân Quyền Thành Công";
        return ResponseEntity.ok(reponse);
    }
}

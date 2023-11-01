package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.user.entities.Favorite;
import com.springleaf_restaurant_backend.user.repositories.FavoriteRepository;

@RestController
@RequestMapping("/api")
public class FavoriteRestController {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/favorites")
    public List<Favorite> favorites() {
        return favoriteRepository.findAll();
    }
}

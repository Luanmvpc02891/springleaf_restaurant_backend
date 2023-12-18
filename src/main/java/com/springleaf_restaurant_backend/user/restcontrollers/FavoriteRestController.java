package com.springleaf_restaurant_backend.user.restcontrollers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Favorite;
import com.springleaf_restaurant_backend.user.service.FavoriteService;

@RestController
public class FavoriteRestController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/public/favorites")
    public List<Favorite> favorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/public/favorite/{favoriteId}")
    public Favorite getOneFavorite(@PathVariable("favoriteId") Long favoriteId){
        return favoriteService.getFavoriteById(favoriteId);
    }

    @PostMapping("/public/create/favorite")
    public Favorite createFavorite(
        //@RequestHeader("Authorization") String jwtToken, 
                                        @RequestBody Favorite favorite){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        Favorite newFavorite = new Favorite();
        newFavorite.setUser(favorite.getUser());
        newFavorite.setMenuItem(favorite.getMenuItem());
        newFavorite.setFavoriteDate(formattedDate);
        return favoriteService.saveFavorite(newFavorite);
    }

    @PutMapping("/public/update/favorite")
    public Favorite updateFavorite(@RequestBody Favorite favorite){
        return favoriteService.saveFavorite(favorite);
    }

    @DeleteMapping("/public/delete/favorite/{favoriteId}")
    public void deleteFavorite(@PathVariable("favoriteId") Long favoriteId){
       favoriteService.deleteFavorite(favoriteId);
    }
}

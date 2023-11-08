package com.springleaf_restaurant_backend.user.service;

import com.springleaf_restaurant_backend.user.entities.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite getFavoriteById(Long id);
    List<Favorite> getAllFavorites();
    Favorite saveFavorite(Favorite favorite);
    Favorite updateFavorite(Favorite favorite);
    void deleteFavorite(Long id);
}

package com.springleaf_restaurant_backend.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springleaf_restaurant_backend.user.entities.Favorite;
import com.springleaf_restaurant_backend.user.repositories.FavoriteRepository;
import com.springleaf_restaurant_backend.user.service.FavoriteService;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Favorite getFavoriteById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite updateFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}

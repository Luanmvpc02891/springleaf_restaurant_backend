package com.springleaf_restaurant_backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.repositories.RatingRepository;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
}

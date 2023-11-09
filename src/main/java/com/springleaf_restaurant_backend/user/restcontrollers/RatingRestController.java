package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springleaf_restaurant_backend.user.entities.Rating;
import com.springleaf_restaurant_backend.user.service.RatingService;

@RestController
public class RatingRestController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/public/ratings")
    public List<Rating> getRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/public/rating/{ratingId}")
    public Rating getRating(@PathVariable("ratingId") Long ratingId){
        return ratingService.getRatingById(ratingId);
    }

    @PostMapping("/public/create/rating")
    public Rating createRating(@RequestBody Rating rating){
        return ratingService.saveRating(rating);
    }

    @PutMapping("/public/update/rating")
    public Rating updateRating(@RequestBody Rating rating){
        if(ratingService.getRatingById(rating.getRatingId()) != null){
            return ratingService.saveRating(rating);
        }else{
            return null;
        }
    }

    @DeleteMapping("/public/delete/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") Long ratingId){
        ratingService.deleteRating(ratingId);
    }
}

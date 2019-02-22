package com.javapractice.springboot.microservices.movieratingsservice.resources;

import com.javapractice.springboot.microservices.movieratingsservice.model.Rating;
import com.javapractice.springboot.microservices.movieratingsservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return new UserRating("Satish", Arrays.asList(
                new Rating("1234", 4),
                new Rating("3456", 4)
        ));
    }
}

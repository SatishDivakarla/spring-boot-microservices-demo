package com.javapractice.springboot.microservices.moviecatalogservice.resources;

import com.javapractice.springboot.microservices.moviecatalogservice.models.CatalogItem;
import com.javapractice.springboot.microservices.moviecatalogservice.models.Movie;
import com.javapractice.springboot.microservices.moviecatalogservice.models.Rating;
import com.javapractice.springboot.microservices.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" +userId, UserRating.class);
        List<Rating> ratingsList = userRating.getRatings();

        return ratingsList.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}

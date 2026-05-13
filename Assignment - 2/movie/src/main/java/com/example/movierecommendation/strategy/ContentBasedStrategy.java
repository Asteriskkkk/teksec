package com.example.movierecommendation.strategy;

import com.example.movierecommendation.model.Movie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Content-Based Strategy
 * Recommends movies based on the attributes of movies the user has liked.
 */
@Component("contentBasedStrategy")
public class ContentBasedStrategy implements RecommendationStrategy {

    @Override
    public List<Movie> getRecommendations(String userId) {
        // Simulated content-based recommendations based on movie attributes
        return Arrays.asList(
                new Movie("cb1", "Avatar", "Sci-Fi/Adventure", 7.8),
                new Movie("cb2", "Dune", "Sci-Fi/Adventure", 8.0),
                new Movie("cb3", "Star Wars: Rogue One", "Sci-Fi/Action", 7.8),
                new Movie("cb4", "The Fifth Element", "Sci-Fi/Action", 7.5)
        );
    }

    @Override
    public String getStrategyName() {
        return "Content-Based";
    }
}

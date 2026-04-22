package com.example.movierecommendation.strategy;

import com.example.movierecommendation.model.Movie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Collaborative Filtering Strategy
 * Recommends movies based on similar users' preferences.
 */
@Component("collaborativeFilteringStrategy")
public class CollaborativeFilteringStrategy implements RecommendationStrategy {

    @Override
    public List<Movie> getRecommendations(String userId) {
        // Simulated collaborative filtering recommendations
        return Arrays.asList(
                new Movie("cf1", "The Matrix Reloaded", "Sci-Fi", 7.2),
                new Movie("cf2", "Inception: Extended Cut", "Sci-Fi/Thriller", 8.8),
                new Movie("cf3", "Interstellar", "Sci-Fi/Drama", 8.7),
                new Movie("cf4", "Blade Runner 2049", "Sci-Fi/Neo-Noir", 8.0)
        );
    }

    @Override
    public String getStrategyName() {
        return "Collaborative Filtering";
    }
}

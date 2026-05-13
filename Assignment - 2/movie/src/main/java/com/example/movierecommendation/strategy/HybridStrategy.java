package com.example.movierecommendation.strategy;

import com.example.movierecommendation.model.Movie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Hybrid Strategy
 * Recommends movies by combining collaborative filtering and content-based approaches.
 */
@Component("hybridStrategy")
public class HybridStrategy implements RecommendationStrategy {

    @Override
    public List<Movie> getRecommendations(String userId) {
        // Simulated hybrid recommendations combining multiple approaches
        return Arrays.asList(
                new Movie("hy1", "Oppenheimer", "Biography/Drama", 8.5),
                new Movie("hy2", "Barbie", "Comedy/Fantasy", 7.0),
                new Movie("hy3", "The Shawshank Redemption", "Drama", 9.3),
                new Movie("hy4", "The Godfather", "Crime/Drama", 9.2),
                new Movie("hy5", "Pulp Fiction", "Crime/Drama", 8.9)
        );
    }

    @Override
    public String getStrategyName() {
        return "Hybrid";
    }
}

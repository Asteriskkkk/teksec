package com.example.movierecommendation.strategy;

import com.example.movierecommendation.model.Movie;
import java.util.List;

/**
 * Common interface for recommendation strategies.
 * All strategy implementations must follow this contract.
 */
public interface RecommendationStrategy {
    /**
     * Get movie recommendations based on a given context.
     * @param userId The user ID for which recommendations are being generated
     * @return A list of recommended movies
     */
    List<Movie> getRecommendations(String userId);

    /**
     * Get the strategy name for display purposes.
     * @return The name of the strategy
     */
    String getStrategyName();
}

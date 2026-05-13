package com.example.movierecommendation.service;

import com.example.movierecommendation.model.Movie;
import com.example.movierecommendation.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Recommendation Service
 * Holds request-specific context and delegates to the injected strategy.
 * This class is instantiated multiple times with different strategy implementations.
 */
@Component
public class RecommendationService {
    private final RecommendationStrategy strategy;
    private final String requestContext;

    /**
     * Constructor for dependency injection with qualifier.
     * Different instances can be created with different strategies using qualifiers.
     * 
     * @param strategy The recommendation strategy to use (injected via qualifier)
     */
    public RecommendationService(@Qualifier("collaborativeFilteringStrategy") RecommendationStrategy strategy) {
        this.strategy = strategy;
        this.requestContext = strategy.getStrategyName() + " Service Instance";
    }

    /**
     * Alternative constructor for explicit strategy injection.
     * Used when creating multiple instances programmatically.
     */
    public RecommendationService(RecommendationStrategy strategy, String contextLabel) {
        this.strategy = strategy;
        this.requestContext = contextLabel;
    }

    /**
     * Get recommendations for a given user ID.
     * Delegates to the injected strategy while maintaining request context.
     * 
     * @param userId The user ID
     * @return List of recommended movies
     */
    public List<Movie> getRecommendationsForUser(String userId) {
        System.out.println("Processing request in context: " + requestContext);
        return strategy.getRecommendations(userId);
    }

    /**
     * Get the strategy name being used by this service.
     * 
     * @return The strategy name
     */
    public String getStrategyName() {
        return strategy.getStrategyName();
    }

    /**
     * Get the request context.
     * 
     * @return The request context label
     */
    public String getRequestContext() {
        return requestContext;
    }
}

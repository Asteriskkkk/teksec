package com.example.movierecommendation.config;

import com.example.movierecommendation.service.RecommendationService;
import com.example.movierecommendation.strategy.CollaborativeFilteringStrategy;
import com.example.movierecommendation.strategy.ContentBasedStrategy;
import com.example.movierecommendation.strategy.HybridStrategy;
import com.example.movierecommendation.strategy.RecommendationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java-based Configuration
 * Defines beans for recommendation services with different strategies.
 * Demonstrates flexible dependency injection using qualifiers.
 */
@Configuration
public class RecommendationConfig {

    /**
     * Recommendation service with Collaborative Filtering strategy.
     */
    @Bean("collaborativeFilteringService")
    public RecommendationService collaborativeFilteringService(
            CollaborativeFilteringStrategy strategy) {
        return new RecommendationService(strategy, "Collaborative Filtering Service Instance");
    }

    /**
     * Recommendation service with Content-Based strategy.
     */
    @Bean("contentBasedService")
    public RecommendationService contentBasedService(
            ContentBasedStrategy strategy) {
        return new RecommendationService(strategy, "Content-Based Service Instance");
    }

    /**
     * Recommendation service with Hybrid strategy.
     */
    @Bean("hybridService")
    public RecommendationService hybridService(
            HybridStrategy strategy) {
        return new RecommendationService(strategy, "Hybrid Service Instance");
    }
}

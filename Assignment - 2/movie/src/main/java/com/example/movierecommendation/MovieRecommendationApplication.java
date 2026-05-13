package com.example.movierecommendation;

import com.example.movierecommendation.model.Movie;
import com.example.movierecommendation.service.RecommendationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Main Application
 * Demonstrates the Movie Recommendation System with pluggable strategies.
 * Shows how Spring's dependency injection and qualifiers enable flexible architecture.
 */
@SpringBootApplication
public class MovieRecommendationApplication {

    public static void main(String[] args) {
        // Create Spring application context with component scanning enabled
        ApplicationContext context = SpringApplication.run(MovieRecommendationApplication.class, args);

        System.out.println("\n========================================");
        System.out.println("Movie Recommendation System Demo");
        System.out.println("========================================\n");

        // Get each recommendation service from the Spring context
        RecommendationService collaborativeService = context.getBean("collaborativeFilteringService", RecommendationService.class);
        RecommendationService contentBasedService = context.getBean("contentBasedService", RecommendationService.class);
        RecommendationService hybridService = context.getBean("hybridService", RecommendationService.class);

        // User ID for testing
        String userId = "user123";

        // Exercise all strategies
        demonstrateStrategy(collaborativeService, userId);
        demonstrateStrategy(contentBasedService, userId);
        demonstrateStrategy(hybridService, userId);

        System.out.println("\n========================================");
        System.out.println("Demo Complete");
        System.out.println("========================================\n");
    }

    /**
     * Helper method to demonstrate a recommendation service.
     * 
     * @param service The recommendation service to demonstrate
     * @param userId The user ID for recommendations
     */
    private static void demonstrateStrategy(RecommendationService service, String userId) {
        System.out.println("Strategy: " + service.getStrategyName());
        System.out.println("Context: " + service.getRequestContext());
        System.out.println("Recommendations for " + userId + ":");
        System.out.println("---");

        List<Movie> recommendations = service.getRecommendationsForUser(userId);
        for (int i = 0; i < recommendations.size(); i++) {
            System.out.println((i + 1) + ". " + recommendations.get(i));
        }

        System.out.println("\n");
    }
}

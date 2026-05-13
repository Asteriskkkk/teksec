package com.example.movierecommendation;

import com.example.movierecommendation.model.Movie;
import com.example.movierecommendation.service.RecommendationService;
import com.example.movierecommendation.strategy.CollaborativeFilteringStrategy;
import com.example.movierecommendation.strategy.ContentBasedStrategy;
import com.example.movierecommendation.strategy.HybridStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRecommendationApplicationTests {

    @Autowired
    @Qualifier("collaborativeFilteringService")
    private RecommendationService collaborativeFilteringService;

    @Autowired
    @Qualifier("contentBasedService")
    private RecommendationService contentBasedService;

    @Autowired
    @Qualifier("hybridService")
    private RecommendationService hybridService;

    @Test
    void testCollaborativeFilteringStrategy() {
        String userId = "testUser1";
        List<Movie> recommendations = collaborativeFilteringService.getRecommendationsForUser(userId);

        assertNotNull(recommendations, "Recommendations should not be null");
        assertFalse(recommendations.isEmpty(), "Recommendations should not be empty");
        assertEquals(4, recommendations.size(), "Should have 4 recommendations");
        assertEquals("Collaborative Filtering", collaborativeFilteringService.getStrategyName());
        assertTrue(recommendations.get(0).getTitle().contains("Matrix"), "First recommendation should contain 'Matrix'");
    }

    @Test
    void testContentBasedStrategy() {
        String userId = "testUser2";
        List<Movie> recommendations = contentBasedService.getRecommendationsForUser(userId);

        assertNotNull(recommendations, "Recommendations should not be null");
        assertFalse(recommendations.isEmpty(), "Recommendations should not be empty");
        assertEquals(4, recommendations.size(), "Should have 4 recommendations");
        assertEquals("Content-Based", contentBasedService.getStrategyName());
        assertTrue(recommendations.get(0).getTitle().contains("Avatar"), "First recommendation should contain 'Avatar'");
    }

    @Test
    void testHybridStrategy() {
        String userId = "testUser3";
        List<Movie> recommendations = hybridService.getRecommendationsForUser(userId);

        assertNotNull(recommendations, "Recommendations should not be null");
        assertFalse(recommendations.isEmpty(), "Recommendations should not be empty");
        assertEquals(5, recommendations.size(), "Should have 5 recommendations");
        assertEquals("Hybrid", hybridService.getStrategyName());
        assertTrue(recommendations.get(0).getTitle().contains("Oppenheimer"), "First recommendation should contain 'Oppenheimer'");
    }

    @Test
    void testMultipleServiceInstances() {
        // Verify that we have three different service instances
        assertNotNull(collaborativeFilteringService, "Collaborative filtering service should not be null");
        assertNotNull(contentBasedService, "Content-based service should not be null");
        assertNotNull(hybridService, "Hybrid service should not be null");

        // Verify they are different instances
        assertNotSame(collaborativeFilteringService, contentBasedService, "Services should be different instances");
        assertNotSame(contentBasedService, hybridService, "Services should be different instances");
        assertNotSame(collaborativeFilteringService, hybridService, "Services should be different instances");
    }

    @Test
    void testStrategyContextManagement() {
        assertTrue(collaborativeFilteringService.getRequestContext().contains("Collaborative Filtering"));
        assertTrue(contentBasedService.getRequestContext().contains("Content-Based"));
        assertTrue(hybridService.getRequestContext().contains("Hybrid"));
    }

    @Test
    void testMovieDataIntegrity() {
        List<Movie> recommendations = collaborativeFilteringService.getRecommendationsForUser("testUser");
        
        for (Movie movie : recommendations) {
            assertNotNull(movie.getId(), "Movie ID should not be null");
            assertNotNull(movie.getTitle(), "Movie title should not be null");
            assertNotNull(movie.getGenre(), "Movie genre should not be null");
            assertTrue(movie.getRating() > 0, "Movie rating should be positive");
            assertFalse(movie.getTitle().isEmpty(), "Movie title should not be empty");
        }
    }
}

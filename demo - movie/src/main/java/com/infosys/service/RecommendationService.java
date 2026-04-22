package com.infosys.service;

import com.infosys.strategy.RecommendationStrategy;
import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;

    public RecommendationService(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<String> getMovies() {
        return strategy.recommendMovies();
    }
}

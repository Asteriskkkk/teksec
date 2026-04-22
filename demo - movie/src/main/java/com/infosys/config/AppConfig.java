package com.infosys.config;

import com.infosys.service.RecommendationService;
import com.infosys.strategy.RecommendationStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.infosys")
public class AppConfig {

    @Bean
    public RecommendationService contentService(
            @Qualifier("content") RecommendationStrategy strategy) {
        return new RecommendationService(strategy);
    }

    @Bean
    public RecommendationService collabService(
            @Qualifier("collab") RecommendationStrategy strategy) {
        return new RecommendationService(strategy);
    }
}

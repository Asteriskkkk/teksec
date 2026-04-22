package com.infosys;

import com.infosys.config.AppConfig;
import com.infosys.service.RecommendationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RecommendationService s1 = context.getBean("contentService", RecommendationService.class);
        RecommendationService s2 = context.getBean("collabService", RecommendationService.class);

        System.out.println("Content: " + s1.getMovies());
        System.out.println("Collaborative: " + s2.getMovies());

        context.close();
    }
}

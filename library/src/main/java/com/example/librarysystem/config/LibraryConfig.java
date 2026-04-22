package com.example.librarysystem.config;

import com.example.librarysystem.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.librarysystem")
@PropertySource("classpath:library-categories.properties")
public class LibraryConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public List<Book> books() {
        return List.of(
                new Book("The Pragmatic Programmer", "Andrew Hunt", "Technology"),
                new Book("Clean Code", "Robert C. Martin", "Technology"),
                new Book("A Brief History of Time", "Stephen Hawking", "Science"),
                new Book("Sapiens", "Yuval Noah Harari", "History"),
                new Book("To Kill a Mockingbird", "Harper Lee", "Fiction")
        );
    }
}
